package com.example.springbootecommerce.service;

import com.example.springbootecommerce.pojo.entity.Contact;
import com.example.springbootecommerce.pojo.requests.ContactRequest;

import java.util.List;

public interface ContactService {
    Contact createContact(ContactRequest contactRequest);
    Contact updateContact(ContactRequest contactRequest, Long id);
    void deleteContact(Long id);
    List<Contact> getListContact();
}
