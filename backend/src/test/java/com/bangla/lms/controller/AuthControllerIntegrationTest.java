package com.bangla.lms.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class AuthControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void adminLoginShouldReturnJwtPayload() throws Exception {
        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "email": "admin@gmail.com",
                                  "password": "Password@123"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").isNotEmpty())
                .andExpect(jsonPath("$.tokenType").value("Bearer"))
                .andExpect(jsonPath("$.email").value("admin@gmail.com"))
                .andExpect(jsonPath("$.role").value("ADMIN"));
    }

    @Test
    void loginShouldRejectInvalidCredentials() throws Exception {
        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "email": "admin@gmail.com",
                                  "password": "WrongPassword@123"
                                }
                                """))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.error").value("Invalid email or password"));
    }

    @Test
    void loginShouldValidateEmailAndPasswordShape() throws Exception {
        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "email": "invalid-email",
                                  "password": ""
                                }
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Validation failed"))
                .andExpect(jsonPath("$.validationErrors.email").value("Email must be valid"))
                .andExpect(jsonPath("$.validationErrors.password").value("Password is required"));
    }

    @Test
    void meShouldReturnCurrentSeededUser() throws Exception {
        String token = loginAndGetToken("student@banglalms.com", "Password@123");

        mockMvc.perform(get("/api/auth/me")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("student@banglalms.com"))
                .andExpect(jsonPath("$.role").value("STUDENT"));
    }

    @Test
    void coursesShouldRequireAuthentication() throws Exception {
        mockMvc.perform(get("/api/courses"))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.error").value("Authentication required"));
    }

    @Test
    void studentShouldAccessCoursesButNotCreateThem() throws Exception {
        String token = loginAndGetToken("student@banglalms.com", "Password@123");

        mockMvc.perform(get("/api/courses")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());

        mockMvc.perform(post("/api/courses")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "title": "Blocked Course",
                                  "description": "Students must not create courses",
                                  "level": "Beginner",
                                  "thumbnailUrl": "https://example.com/x.jpg",
                                  "price": 0
                                }
                                """))
                .andExpect(status().isForbidden());
    }

    @Test
    void teacherShouldCreateCourse() throws Exception {
        String token = loginAndGetToken("teacher@banglalms.com", "Password@123");

        mockMvc.perform(post("/api/courses")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "title": "Teacher Managed Course",
                                  "description": "Created by teacher",
                                  "level": "Intermediate",
                                  "thumbnailUrl": "https://example.com/teacher.jpg",
                                  "price": 19.99
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Teacher Managed Course"));
    }

    @Test
    void adminShouldManageUsersEndToEnd() throws Exception {
        String adminToken = loginAndGetToken("admin@gmail.com", "Password@123");
        long studentId = getUserIdByEmail(adminToken, "student@banglalms.com");
        long teacherId = getUserIdByEmail(adminToken, "teacher@banglalms.com");

        mockMvc.perform(patch("/api/admin/users/{userId}/status", studentId)
                        .header("Authorization", "Bearer " + adminToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "active": false
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.active").value(false));

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "email": "student@banglalms.com",
                                  "password": "Password@123"
                                }
                                """))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.error").value("User account is deactivated"));

        mockMvc.perform(post("/api/admin/users/{userId}/courses", teacherId)
                        .header("Authorization", "Bearer " + adminToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "courseId": 1
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.assignedCourses[0].id").value(1));
    }

    private String loginAndGetToken(String email, String password) throws Exception {
        String responseBody = mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "email": "%s",
                                  "password": "%s"
                                }
                                """.formatted(email, password)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        return objectMapper.readTree(responseBody).get("token").asText();
    }

    private long getUserIdByEmail(String adminToken, String email) throws Exception {
        String responseBody = mockMvc.perform(get("/api/admin/users")
                        .header("Authorization", "Bearer " + adminToken))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        JsonNode users = objectMapper.readTree(responseBody);
        for (JsonNode user : users) {
            if (email.equals(user.get("email").asText())) {
                return user.get("id").asLong();
            }
        }

        throw new IllegalArgumentException("User not found: " + email);
    }
}
