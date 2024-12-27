package com.dio.user_service.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dio.user_service.entities.Role;
import com.dio.user_service.services.RoleService;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public List<Role> showListRoles() {
        return roleService.getAllRoles();
    }

    @PostMapping("/add")
    public ResponseEntity<Role> addRole(@RequestBody Role role) {
        Role createdRole = roleService.addRole(role);
        return ResponseEntity.ok(createdRole);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        Optional<Role> role = roleService.getRoleById(id);
        if (role.isPresent()) {
            roleService.deleteRole(role.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}