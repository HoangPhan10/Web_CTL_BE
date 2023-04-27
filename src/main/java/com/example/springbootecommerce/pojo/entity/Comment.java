package com.example.springbootecommerce.pojo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
@Entity
@Table(name = "comment")
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length =200)
    private String comment;
    @Column(nullable = false, length =5)
    private int rate ;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    @ManyToOne(fetch = FetchType.EAGER)
    private Product product;
    private Date createdAt;
    private Date updatedAt;
}
