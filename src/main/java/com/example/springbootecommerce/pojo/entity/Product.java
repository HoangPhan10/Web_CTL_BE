package com.example.springbootecommerce.pojo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "product")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length =100)
    private String title;

    @Column(nullable = false, length =20)
    private Float price;

    @Column(nullable = false, length =50)
    private String description;

    @Column(nullable = false, length =20)
    private long quantity;
    private Date createdAt;
    private Date updatedAt;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private User user;

}
