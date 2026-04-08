package com.bangla.lms.controller;

import com.bangla.lms.dto.AdminUserManagementResponse;
import com.bangla.lms.dto.CourseAssignmentRequest;
import com.bangla.lms.dto.UserStatusUpdateRequest;
import com.bangla.lms.service.AdminUserManagementService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/users")
@RequiredArgsConstructor
public class AdminUserManagementController {

    private final AdminUserManagementService adminUserManagementService;

    @GetMapping
    public ResponseEntity<List<AdminUserManagementResponse>> getAllUsers() {
        return ResponseEntity.ok(adminUserManagementService.getAllUsers());
    }

    @PatchMapping("/{userId}/status")
    public ResponseEntity<AdminUserManagementResponse> updateUserStatus(
            @PathVariable Long userId,
            @Valid @RequestBody UserStatusUpdateRequest request
    ) {
        return ResponseEntity.ok(adminUserManagementService.updateUserStatus(userId, request.getActive()));
    }

    @PostMapping("/{userId}/courses")
    public ResponseEntity<AdminUserManagementResponse> assignCourse(
            @PathVariable Long userId,
            @Valid @RequestBody CourseAssignmentRequest request
    ) {
        return ResponseEntity.ok(adminUserManagementService.assignCourse(userId, request.getCourseId()));
    }
}
