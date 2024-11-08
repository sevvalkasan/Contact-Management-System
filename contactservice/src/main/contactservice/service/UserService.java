package com.example.contactservice.service;

import com.example.contactservice.model.User;
import com.example.contactservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    public User updateUser(Long id, User updatedUser) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setUsername(updatedUser.getUsername());
                    user.setEmail(updatedUser.getEmail());
                    user.setPhone(updatedUser.getPhone());
                    user.setSurname(updatedUser.getSurname());
                    return userRepository.save(user);
                })
                .orElse(null);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User getUserByPhone(String phone) {
        return userRepository.findByPhone(phone);
    }

    public User getUserBySurname(String surname) {
        return userRepository.findBySurname(surname);
    }
    public String resetPassword(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            String newPassword = UUID.randomUUID().toString();
            user.setPassword(newPassword);
            userRepository.save(user);
            emailService.sendEmail(user.getEmail(), "Yeni Parolanız", "Yeni parolanız: " + newPassword);
            return "Yeni parola e-posta adresinize gönderildi.";
        } else {
            return "E-posta adresi bulunamadı.";
        }
    }

}
