package com.bangla.lms.repository;

import com.bangla.lms.entity.UserCourseAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserCourseAssignmentRepository extends JpaRepository<UserCourseAssignment, Long> {
    List<UserCourseAssignment> findByUserId(Long userId);
    Optional<UserCourseAssignment> findByUserIdAndCourseId(Long userId, Long courseId);
}
