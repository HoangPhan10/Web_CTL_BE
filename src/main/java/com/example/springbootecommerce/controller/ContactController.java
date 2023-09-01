package com.example.springbootecommerce.controller;

import com.example.springbootecommerce.pojo.entity.Contact;
import com.example.springbootecommerce.pojo.requests.ContactRequest;
import com.example.springbootecommerce.pojo.responses.ObjectResponse;
import com.example.springbootecommerce.pojo.responses.ProductPageResponse;
import com.example.springbootecommerce.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contact")
@CrossOrigin(maxAge = 3600)
public class ContactController {
    @Autowired
    private ContactService contactService;
    @GetMapping("")
    public ResponseEntity<ObjectResponse> listContact() {
        List<Contact> contacts = contactService.getListContact();
        return ResponseEntity.ok().body(
                new ObjectResponse(HttpStatus.CREATED,"Get List Contact successfully",contacts)
        );
    }
    @PostMapping("/save")
    public ResponseEntity<ObjectResponse> createContact(@RequestBody ContactRequest contactRequest) {
        Contact contact = contactService.createContact(contactRequest);
        return ResponseEntity.ok().body(
                new ObjectResponse(HttpStatus.CREATED,"Create Contact successfully",contact)
        );
    }
    @PutMapping("/update")
    public ResponseEntity<ObjectResponse> updateContact(@RequestBody ContactRequest contactRequest, @RequestParam("id") Long id) {
        Contact contact = contactService.updateContact(contactRequest,id);
        return ResponseEntity.ok().body(
                new ObjectResponse(HttpStatus.CREATED,"Update Contact successfully",contact)
        );
    }
    @DeleteMapping("/delete")
    public ResponseEntity<ObjectResponse> deleteContact(@RequestParam("id") Long id) {
        contactService.deleteContact(id);
        return ResponseEntity.ok().body(
                new ObjectResponse(HttpStatus.CREATED,"Delete Contact successfully",null)
        );
    }
}
