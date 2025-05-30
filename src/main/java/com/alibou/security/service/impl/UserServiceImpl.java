package com.alibou.security.service.impl;

import com.alibou.security.repository.UserRepository;
import com.alibou.security.service.UserService;
import com.alibou.security.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEmailVerified(false);
        user.setFailedAttempt(0);
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User updateUser(Integer id, User user) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setFirstname(user.getFirstname());
                    existingUser.setLastname(user.getLastname());
                    existingUser.setPhoneNumber(user.getPhoneNumber());
                    existingUser.setDepartment(user.getDepartment());
                    if (user.getPassword() != null && !user.getPassword().isEmpty()) {
                        existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
                    }
                    return userRepository.save(existingUser);
                })
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    @Override
    @Transactional
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    @Override
    @Transactional
    public void updateLastLogin(Integer userId) {
        userRepository.findById(userId).ifPresent(user -> {
            user.setLastLogin(LocalDateTime.now());
            userRepository.save(user);
        });
    }

    @Override
    @Transactional
    public void incrementFailedAttempt(Integer userId) {
        userRepository.findById(userId).ifPresent(user -> {
            user.setFailedAttempt(user.getFailedAttempt() + 1);
            if (user.getFailedAttempt() >= 3) {
                lockUser(userId);
            }
            userRepository.save(user);
        });
    }

    @Override
    @Transactional
    public void resetFailedAttempt(Integer userId) {
        userRepository.findById(userId).ifPresent(user -> {
            user.setFailedAttempt(0);
            userRepository.save(user);
        });
    }

    @Override
    @Transactional
    public void lockUser(Integer userId) {
        userRepository.findById(userId).ifPresent(user -> {
            user.setLockTime(LocalDateTime.now().plusMinutes(30));
            userRepository.save(user);
        });
    }

    @Override
    @Transactional
    public void unlockUser(Integer userId) {
        userRepository.findById(userId).ifPresent(user -> {
            user.setLockTime(null);
            user.setFailedAttempt(0);
            userRepository.save(user);
        });
    }
} 