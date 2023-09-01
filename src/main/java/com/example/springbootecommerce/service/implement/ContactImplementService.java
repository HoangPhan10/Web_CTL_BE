package com.example.springbootecommerce.service.implement;

import com.example.springbootecommerce.exception.UserNotFoundException;
import com.example.springbootecommerce.pojo.entity.Contact;
import com.example.springbootecommerce.pojo.requests.ContactRequest;
import com.example.springbootecommerce.repository.ContactRepository;
import com.example.springbootecommerce.service.ContactService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ContactImplementService implements ContactService {
    @Autowired
    private ContactRepository contactRepository;
    @Override
    public Contact createContact(ContactRequest contactRequest) {
        Contact contact = new Contact();
        contact.setName(contactRequest.getName());
        contact.setPhone(contactRequest.getPhone());
        return contactRepository.save(contact);
    }

    @Override
    public Contact updateContact(ContactRequest contactRequest, Long id) {
        Contact contact = contactRepository.findContactById(id);
        if (contact == null) {
            throw new UserNotFoundException("Contact not found for id " + id);
        }
        contact.setName(contactRequest.getName());
        contact.setPhone(contactRequest.getPhone());
        return contactRepository.save(contact);
    }

    @Override
    public void deleteContact(Long id) {
        Contact contact = contactRepository.findContactById(id);
        if (contact == null) {
            throw new UserNotFoundException("Contact not found for id " + id);
        }
        contactRepository.delete(contact);
    }

    @Override
    public List<Contact> getListContact() {
        List<Contact> contacts = contactRepository.findAll();
        return contacts;
    }

}
