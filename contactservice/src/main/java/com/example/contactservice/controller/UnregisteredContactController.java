package com.example.contactservice.controller;

import com.example.contactservice.model.ContactRequest;
import com.example.contactservice.service.UnregisteredContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/unregistered-contact")
public class UnregisteredContactController {

    @Autowired
    private UnregisteredContactService unregisteredContactService;

    @PostMapping
    public ResponseEntity<String> receiveContactRequest(@RequestBody ContactRequest contactRequest) {
        try {
            String responseMessage = unregisteredContactService.receiveContactRequest(contactRequest);
            return ResponseEntity.ok(responseMessage);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
