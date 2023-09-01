package com.example.springbootecommerce.pojo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "products")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 65535)
    private String url;
    @Column(length = 65535)
    private String title;
    @Column(length = 65535)
    private String parent;
    @Column(length = 65535)
    private String type;
    @Column(length = 65535)
    private String description;
    private boolean status = true;
    @Column(length = 65535)
    private String html_description;
}
