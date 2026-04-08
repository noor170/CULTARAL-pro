package com.bangla.lms.controller;

import com.bangla.lms.dto.AuthResponse;
import com.bangla.lms.dto.LoginRequest;
import com.bangla.lms.dto.RegisterRequest;
import com.bangla.lms.dto.UserProfileResponse;
import com.bangla.lms.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        AuthResponse response = userService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        AuthResponse response = userService.login(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/me")
    public ResponseEntity<UserProfileResponse> me(@AuthenticationPrincipal UserDetails userDetails) {
        UserProfileResponse response = userService.getCurrentUser(userDetails.getUsername());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/admin/dashboard")
    public ResponseEntity<Map<String, String>> adminDashboard() {
        return ResponseEntity.ok(Map.of("message", "Admin access granted"));
    }

    @GetMapping("/student/dashboard")
    public ResponseEntity<Map<String, String>> studentDashboard() {
        return ResponseEntity.ok(Map.of("message", "Student access granted"));
    }

    @GetMapping("/teacher/dashboard")
    public ResponseEntity<Map<String, String>> teacherDashboard() {
        return ResponseEntity.ok(Map.of("message", "Teacher access granted"));
    }
}
