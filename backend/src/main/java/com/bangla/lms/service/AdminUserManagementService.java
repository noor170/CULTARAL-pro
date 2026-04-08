package com.bangla.lms.service;

import com.bangla.lms.dto.AdminUserManagementResponse;
import com.bangla.lms.dto.CourseDTO;
import com.bangla.lms.entity.Course;
import com.bangla.lms.entity.User;
import com.bangla.lms.entity.UserCourseAssignment;
import com.bangla.lms.exception.UserNotFoundException;
import com.bangla.lms.repository.CourseRepository;
import com.bangla.lms.repository.UserCourseAssignmentRepository;
import com.bangla.lms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminUserManagementService {

    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final UserCourseAssignmentRepository userCourseAssignmentRepository;

    @Transactional(readOnly = true)
    public List<AdminUserManagementResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public AdminUserManagementResponse updateUserStatus(Long userId, boolean active) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));
        user.setActive(active);
        return toResponse(userRepository.save(user));
    }

    @Transactional
    public AdminUserManagementResponse assignCourse(Long userId, Long courseId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Course not found with id: " + courseId));

        if (user.getRole() == User.Role.ADMIN) {
            throw new IllegalArgumentException("Courses can only be assigned to STUDENT or TEACHER users");
        }

        userCourseAssignmentRepository.findByUserIdAndCourseId(userId, courseId)
                .orElseGet(() -> userCourseAssignmentRepository.save(
                        UserCourseAssignment.builder()
                                .user(user)
                                .course(course)
                                .build()
                ));

        return toResponse(user);
    }

    private AdminUserManagementResponse toResponse(User user) {
        List<CourseDTO> assignedCourses = userCourseAssignmentRepository.findByUserId(user.getId()).stream()
                .map(UserCourseAssignment::getCourse)
                .map(this::toCourseDto)
                .collect(Collectors.toList());

        return AdminUserManagementResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phone(user.getPhone())
                .gender(user.getGender())
                .role(user.getRole().name())
                .active(user.isActive())
                .assignedCourses(assignedCourses)
                .build();
    }

    private CourseDTO toCourseDto(Course course) {
        return new CourseDTO(
                course.getId(),
                course.getTitle(),
                course.getDescription(),
                course.getLevel(),
                course.getThumbnailUrl(),
                course.getPrice(),
                course.getLessons() == null ? 0 : course.getLessons().size()
        );
    }
}
