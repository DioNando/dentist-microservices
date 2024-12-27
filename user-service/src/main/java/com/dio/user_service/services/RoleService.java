package com.dio.user_service.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dio.user_service.entities.Role;
import com.dio.user_service.repositories.RoleRepository;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role addRole(Role role) {
        return roleRepository.save(role);
    }

    public void deleteRole(Role role) {
        roleRepository.delete(role);
    }

    public Optional<Role> getRoleById(Long id) {
        return roleRepository.findById(id);
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
}
