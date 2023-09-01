package com.example.springbootecommerce.pojo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "portfolio")
@Data
public class Portfolio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 65535)
    private String title;
    @Column(length = 65535)
    private String url;
    private boolean status = true;
    @Column(length = 65535)
    private String description;
}
