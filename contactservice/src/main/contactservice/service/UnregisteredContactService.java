package com.example.contactservice.service;

import com.example.contactservice.model.ContactRequest;
import com.example.contactservice.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UnregisteredContactService {

    @Autowired
    private ContactRepository contactRepository;

    public String receiveContactRequest(ContactRequest contactRequest) {
        // Kayıtsız kullanıcılar için user alanı null
        contactRequest.setUser(null);
        contactRepository.save(contactRequest);
        return "İletişim talebiniz başarıyla alındı";
    }
}
