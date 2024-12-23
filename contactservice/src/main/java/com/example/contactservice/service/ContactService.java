package com.example.contactservice.service;

import com.example.contactservice.model.ContactRequest;
import com.example.contactservice.model.User;
import com.example.contactservice.repository.ContactRepository;
import com.example.contactservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private UserRepository userRepository;

    public String receiveContactRequest(ContactRequest contactRequest) {
        if (contactRequest.getUsername() != null) {
            Optional<User> optionalUser = userRepository.findByUsername(contactRequest.getUsername());
            if (optionalUser.isPresent()) {
                contactRequest.setUser(optionalUser.get());
            } else {
                contactRequest.setUser(null);
            }
        } else {
            contactRequest.setUser(null);
        }
        contactRepository.save(contactRequest);
        return "Contact request has been successfully received";
    }

    public List<ContactRequest> getUnregisteredContacts() {
        return contactRepository.findByUserIsNull();
    }

    public List<ContactRequest> getAllContacts() {
        return contactRepository.findAll();
    }

    public String updateContactRequest(Long id, ContactRequest updatedContactRequest) {
        if (contactRepository.existsById(id)) {
            updatedContactRequest.setId(id);
            contactRepository.save(updatedContactRequest);
            return "Contact request updated";
        }
        return "Contact request not found";
    }

    public boolean deleteContactRequest(Long id) {
        if (contactRepository.existsById(id)) {
            contactRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
