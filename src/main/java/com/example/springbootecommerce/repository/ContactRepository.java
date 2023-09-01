package com.example.springbootecommerce.repository;

import com.example.springbootecommerce.pojo.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact,Long> {
    Contact findContactById(long id);
}
