package com.example.springbootecommerce.pojo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "contact")
@Data
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String phone;
}
