package com.dio.user_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dio.user_service.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    
}
