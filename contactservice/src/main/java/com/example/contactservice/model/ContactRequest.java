package com.example.contactservice.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Table(name = "contact_requests")
@Data
@NoArgsConstructor
public class ContactRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String email;
    private String phone;
    private String name;
    private String surname;
    private String message;
    private String username;
    private String subject; // Yeni konu alanı

    @Column(name = "created_at", updatable = false, nullable = false)
    private Timestamp createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Timestamp(System.currentTimeMillis());
    }

    public String getUsername() {
        return username;
    }
}
