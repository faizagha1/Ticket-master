package com.UserService.Services;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.UserService.Dtos.Request.SigninReq;
import com.UserService.Dtos.Request.SignupReq;
import com.UserService.Dtos.Response.SigninResp;
import com.UserService.Dtos.Response.SignupResp;
import com.UserService.Entities.Role;
import com.UserService.Entities.User;
import com.UserService.Exceptions.UserNotFound;
import com.UserService.Repository.UserRepository;
import com.UserService.Utils.JwtUtil;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwt;
    private Integer expirationTime = 15;

    public String generateVerificationCode() {
        Integer otpValue = ThreadLocalRandom.current().nextInt(0, 100000);
        return String.format("%05d", otpValue);
    }

    @Transactional
    public SignupResp signup(SignupReq signupReq) {
        if (userRepository.existsByEmail(signupReq.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        if (userRepository.existsByEmail(signupReq.getUserName())) {
            throw new RuntimeException("Username already exists");
        }
        Set<Role> role = Set.of(Role.USER);
        String verificationCode = generateVerificationCode();
        LocalDateTime verificationCodeCreatedAt = LocalDateTime.now();
        LocalDateTime verificationCodeExpireAt = LocalDateTime.now().plusMinutes(expirationTime);
        User user = User
                .builder()
                .firstName(signupReq.getFirstName())
                .lastName(signupReq.getLastName())
                .userName(signupReq.getUserName())
                .email(signupReq.getEmail())
                .role(role)
                .profilePicture(signupReq.getProfilePicture())
                .password(passwordEncoder.encode(signupReq.getPassword()))
                .verificationCode(verificationCode)
                .verificationCodeCreatedAt(verificationCodeCreatedAt)
                .verificationCodeExpireAt(verificationCodeExpireAt)
                .build();

        userRepository.save(user);
        String message = "User with email and username" + user.getEmail() + " and " + user.getUserName()
                + " has been created";

        return SignupResp
                .builder()
                .email(user.getEmail())
                .username(user.getUserName())
                .message(message)
                .build();
    }

    public SigninResp login(SigninReq signinReq) {
        User user = userRepository.findByEmail(signinReq.getEmail())
                .orElseThrow(() -> new UserNotFound("User not found"));
        if (!passwordEncoder.matches(signinReq.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        String token = jwt.generateToken(signinReq.getEmail(), user.getRole());
        String message = "Login of email" + signinReq.getEmail() + " successful";
        return SigninResp
                .builder()
                .token(token)
                .message(message)
                .build();
    }

    public Boolean verifyEmail(String verificationCode, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFound("User not found"));
        if (user.getVerificationCode().equals(verificationCode)
                && user.getVerificationCodeExpireAt().isAfter(LocalDateTime.now())) {
            user.setInEmailVerified(true);
            userRepository.save(user);
            return true;
        }
        return false;
    }
}
