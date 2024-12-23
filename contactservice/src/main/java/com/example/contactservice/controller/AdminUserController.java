package com.example.contactservice.controller;

import com.example.contactservice.model.AdminUser;
import com.example.contactservice.repository.AdminUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminUserController {

    @Autowired
    private AdminUserRepository adminUserRepository;

    @PostMapping("/login")
    public ResponseEntity<AdminUser> login(@RequestBody AdminUser adminUser) {
        AdminUser foundUser = adminUserRepository.findByUsername(adminUser.getUsername());
        if (foundUser != null && foundUser.getPassword().equals(adminUser.getPassword())) {
            return ResponseEntity.ok(foundUser);
        }
        return ResponseEntity.status(401).body(null);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<AdminUser> getAdminByUsername(@PathVariable String username) {
        AdminUser adminUser = adminUserRepository.findByUsername(username);
        return adminUser != null ? ResponseEntity.ok(adminUser) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<AdminUser>> getAllAdmins() {
        List<AdminUser> adminUsers = adminUserRepository.findAll();
        return ResponseEntity.ok(adminUsers);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<AdminUser> getAdminById(@PathVariable Long id) {
        Optional<AdminUser> adminUser = adminUserRepository.findById(id);
        return adminUser.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AdminUser> createAdmin(@RequestBody AdminUser adminUser) {
        AdminUser savedAdmin = adminUserRepository.save(adminUser);
        return ResponseEntity.ok(savedAdmin);
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<AdminUser> updateAdmin(@PathVariable Long id, @RequestBody AdminUser adminDetails) {
        Optional<AdminUser> optionalAdminUser = adminUserRepository.findById(id);
        if (optionalAdminUser.isPresent()) {
            AdminUser adminUser = optionalAdminUser.get();
            adminUser.setUsername(adminDetails.getUsername());
            adminUser.setPassword(adminDetails.getPassword());
            adminUser.setEmail(adminDetails.getEmail());
            adminUser.setRole(adminDetails.getRole());
            AdminUser updatedAdmin = adminUserRepository.save(adminUser);
            return ResponseEntity.ok(updatedAdmin);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Long id) {
        Optional<AdminUser> optionalAdminUser = adminUserRepository.findById(id);
        if (optionalAdminUser.isPresent()) {
            adminUserRepository.delete(optionalAdminUser.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
