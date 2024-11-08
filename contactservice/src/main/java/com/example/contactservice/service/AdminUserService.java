package com.example.contactservice.service;

import com.example.contactservice.model.AdminUser;
import com.example.contactservice.repository.AdminUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminUserService {

    @Autowired
    private AdminUserRepository adminUserRepository;

    public AdminUser findByUsername(String username) {
        return adminUserRepository.findByUsername(username);
    }

    public void save(AdminUser adminUser) {
        adminUserRepository.save(adminUser);
    }
}
