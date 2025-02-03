package com.UserService.Services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.UserService.Dtos.Request.SignupReq;
import com.UserService.Dtos.Response.UserResponse;
import com.UserService.Entities.User;
import com.UserService.Exceptions.UserNotFound;
import com.UserService.Repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User getOwnAccount(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFound("User not found"));
        return user;
    }

    public UserResponse getUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFound("User not found"));
        return UserResponse
                .builder()
                .userName(user.getUserName())
                .profilePicture(user.getProfilePicture())
                .role(user.getRole())
                .createdAt(user.getCreatedAt())
                .build();
    }

    public String updateUser(Long userId, Long userCurr, SignupReq updateReq) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFound("User not found"));
        if (user.getId() != userCurr) {
            throw new IllegalArgumentException("User not authorized");
        }
        user.setUserName(updateReq.getUserName());
        user.setProfilePicture(updateReq.getProfilePicture());
        user.setPassword(passwordEncoder.encode(updateReq.getPassword()));
        userRepository.save(user);
        return "User updated successfully";
    }

    public String deleteUser(Long userId, User userOriginal) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFound("User not found"));
        if (user.getId() != userOriginal.getId()) {
            throw new IllegalArgumentException("User not authorized");
        }
        userRepository.delete(user);
        return "user deleted successfully";
    }
}
