package com.UserService.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.UserService.Entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String userName);

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}
