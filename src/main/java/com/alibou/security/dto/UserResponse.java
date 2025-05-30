package com.alibou.security.dto;

import com.alibou.security.user.Role;
import lombok.Data;
import lombok.Builder;

import java.time.LocalDateTime;

@Data
@Builder
public class UserResponse {
    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private Role role;
    private String phoneNumber;
    private String department;
    private boolean emailVerified;
    private LocalDateTime lastLogin;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
} 