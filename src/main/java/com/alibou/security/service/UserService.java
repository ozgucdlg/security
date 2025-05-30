package com.alibou.security.service;

import com.alibou.security.user.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(User user);
    User updateUser(Integer id, User user);
    void deleteUser(Integer id);
    Optional<User> getUserById(Integer id);
    Optional<User> getUserByEmail(String email);
    List<User> getAllUsers();
    boolean existsByEmail(String email);
    void updateLastLogin(Integer userId);
    void incrementFailedAttempt(Integer userId);
    void resetFailedAttempt(Integer userId);
    void lockUser(Integer userId);
    void unlockUser(Integer userId);
} 