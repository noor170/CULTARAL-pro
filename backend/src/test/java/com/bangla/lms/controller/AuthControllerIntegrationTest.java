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
    void registerShouldCreateUserAndReturnJwtPayload() throws Exception {
        String requestBody = """
                {
                  "firstName": "Test",
                  "lastName": "User",
                  "email": "test.user@example.com",
                  "password": "Password1!",
                  "phone": "+8801700000000",
                  "gender": "female"
                }
                """;

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.token").isNotEmpty())
                .andExpect(jsonPath("$.tokenType").value("Bearer"))
                .andExpect(jsonPath("$.expiresIn").value(86400000))
                .andExpect(jsonPath("$.email").value("test.user@example.com"))
                .andExpect(jsonPath("$.role").value("STUDENT"));
    }

    @Test
    void loginShouldReturnJwtForExistingUser() throws Exception {
        String requestBody = """
                {
                  "email": "student@banglalms.com",
                  "password": "Password@123"
                }
                """;

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").isNotEmpty())
                .andExpect(jsonPath("$.tokenType").value("Bearer"))
                .andExpect(jsonPath("$.email").value("student@banglalms.com"))
                .andExpect(jsonPath("$.role").value("STUDENT"));
    }

    @Test
    void meShouldRequireJwtAndReturnAuthenticatedUser() throws Exception {
        String token = registerUser("me.user@example.com");

        mockMvc.perform(get("/api/auth/me")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("me.user@example.com"))
                .andExpect(jsonPath("$.firstName").value("Test"))
                .andExpect(jsonPath("$.role").value("STUDENT"));
    }

    @Test
    void meShouldReturnUnauthorizedWithoutJwt() throws Exception {
        mockMvc.perform(get("/api/auth/me"))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.error").value("Authentication required"))
                .andExpect(jsonPath("$.path").value("/api/auth/me"));
    }

    @Test
    void seededAdminShouldAccessAdminEndpoint() throws Exception {
        String token = loginAndGetToken("admin@gmail.com", "Password@123");

        mockMvc.perform(get("/api/auth/admin/dashboard")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Admin access granted"));
    }

    @Test
    void seededTeacherShouldAccessTeacherEndpoint() throws Exception {
        String token = loginAndGetToken("teacher@banglalms.com", "Password@123");

        mockMvc.perform(get("/api/auth/teacher/dashboard")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Teacher access granted"));
    }

    @Test
    void seededStudentShouldBeForbiddenFromAdminEndpoint() throws Exception {
        String token = loginAndGetToken("student@banglalms.com", "Password@123");

        mockMvc.perform(get("/api/auth/admin/dashboard")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isForbidden());
    }

    @Test
    void adminShouldListUsersForManagement() throws Exception {
        String token = loginAndGetToken("admin@gmail.com", "Password@123");

        mockMvc.perform(get("/api/admin/users")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].email").exists());
    }

    @Test
    void adminShouldDeactivateStudentAndBlockLogin() throws Exception {
        String adminToken = loginAndGetToken("admin@gmail.com", "Password@123");

        mockMvc.perform(get("/api/admin/users")
                        .header("Authorization", "Bearer " + adminToken))
                .andExpect(status().isOk())
                .andDo(result -> {
                    JsonNode users = objectMapper.readTree(result.getResponse().getContentAsString());
                    long studentId = findUserId(users, "student@banglalms.com");

                    mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch("/api/admin/users/{userId}/status", studentId)
                                    .header("Authorization", "Bearer " + adminToken)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content("""
                                            {
                                              "active": false
                                            }
                                            """))
                            .andExpect(status().isOk())
                            .andExpect(jsonPath("$.active").value(false));
                });

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
    }

    @Test
    void adminShouldAssignCourseToTeacher() throws Exception {
        String adminToken = loginAndGetToken("admin@gmail.com", "Password@123");
        long teacherId = getUserIdByEmail(adminToken, "teacher@banglalms.com");

        mockMvc.perform(post("/api/admin/users/{userId}/courses", teacherId)
                        .header("Authorization", "Bearer " + adminToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "courseId": 1
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("teacher@banglalms.com"))
                .andExpect(jsonPath("$.assignedCourses[0].id").value(1));
    }

    @Test
    void adminShouldNotAssignCourseToAdminUser() throws Exception {
        String adminToken = loginAndGetToken("admin@gmail.com", "Password@123");
        long adminId = getUserIdByEmail(adminToken, "admin@gmail.com");

        mockMvc.perform(post("/api/admin/users/{userId}/courses", adminId)
                        .header("Authorization", "Bearer " + adminToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "courseId": 1
                                }
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Courses can only be assigned to STUDENT or TEACHER users"));
    }

    private String registerUser(String email) throws Exception {
        String requestBody = """
                {
                  "firstName": "Test",
                  "lastName": "User",
                  "email": "%s",
                  "password": "Password1!",
                  "phone": "+8801700000000",
                  "gender": "female"
                }
                """.formatted(email);

        String responseBody = mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        JsonNode jsonNode = objectMapper.readTree(responseBody);
        return jsonNode.get("token").asText();
    }

    private String loginAndGetToken(String email, String password) throws Exception {
        String requestBody = """
                {
                  "email": "%s",
                  "password": "%s"
                }
                """.formatted(email, password);

        String responseBody = mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        JsonNode jsonNode = objectMapper.readTree(responseBody);
        return jsonNode.get("token").asText();
    }

    private long getUserIdByEmail(String adminToken, String email) throws Exception {
        String responseBody = mockMvc.perform(get("/api/admin/users")
                        .header("Authorization", "Bearer " + adminToken))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        JsonNode users = objectMapper.readTree(responseBody);
        return findUserId(users, email);
    }

    private long findUserId(JsonNode users, String email) {
        for (JsonNode user : users) {
            if (email.equals(user.get("email").asText())) {
                return user.get("id").asLong();
            }
        }
        throw new IllegalArgumentException("User not found in response: " + email);
    }
}
