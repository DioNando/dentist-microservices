package com.dio.user_service.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dio.user_service.entities.User;
import com.dio.user_service.repositories.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<User> searchUsersByFullName(String fullName) {
        return userRepository.findByFullNameContaining(fullName);
    }
}
