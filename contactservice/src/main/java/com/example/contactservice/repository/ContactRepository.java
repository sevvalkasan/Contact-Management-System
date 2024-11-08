package com.example.contactservice.repository;

import com.example.contactservice.model.ContactRequest;
import com.example.contactservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<ContactRequest, Long> {
    List<ContactRequest> findByUserIsNull();
    List<ContactRequest> findByUser(User user);
}
