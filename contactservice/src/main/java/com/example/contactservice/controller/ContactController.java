package com.example.contactservice.controller;

import com.example.contactservice.model.ContactRequest;
import com.example.contactservice.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contact")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping
    public ResponseEntity<String> receiveContactRequest(@RequestBody ContactRequest contactRequest) {
        try {
            String responseMessage = contactService.receiveContactRequest(contactRequest);
            return ResponseEntity.ok(responseMessage);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @GetMapping
    public List<ContactRequest> getAllContacts() {
        return contactService.getAllContacts();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateContactRequest(
            @PathVariable Long id,
            @RequestBody ContactRequest updatedContactRequest) {
        String responseMessage = contactService.updateContactRequest(id, updatedContactRequest);
        if ("Contact request updated".equals(responseMessage)) {
            return ResponseEntity.ok(responseMessage);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMessage);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteContactRequest(@PathVariable Long id) {
        boolean deleted = contactService.deleteContactRequest(id);
        if (deleted) {
            return ResponseEntity.ok("Contact request deleted");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contact request not found");
    }

}
