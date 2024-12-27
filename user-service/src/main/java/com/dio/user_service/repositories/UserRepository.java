package com.dio.user_service.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dio.user_service.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByFullNameContaining(String fullName);
}