package com.UserService.Dtos.Response;

import java.time.LocalDateTime;

import com.UserService.Entities.Role;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdminResponse {
    private String userName;
    private String profilePicture;
    private Role role;
    private LocalDateTime createdAt;
}
