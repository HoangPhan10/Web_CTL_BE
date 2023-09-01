package com.example.springbootecommerce.pojo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "youtube")
@Data
public class Youtube {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 65535)
    private String url;
    @ManyToOne(fetch = FetchType.EAGER)
    private Product product;
}
