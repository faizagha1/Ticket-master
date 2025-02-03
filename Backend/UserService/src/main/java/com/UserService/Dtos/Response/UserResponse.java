package com.UserService.Dtos.Response;

import java.time.LocalDateTime;
import java.util.Set;

import com.UserService.Entities.Role;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {
    private String userName;
    private String profilePicture;
    private Set<Role> role;
    private LocalDateTime createdAt;
}
