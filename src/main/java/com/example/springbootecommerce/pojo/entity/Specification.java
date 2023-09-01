package com.example.springbootecommerce.pojo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "specification")
@Data
public class Specification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "_key")
    private String key;
    @Column(name = "_value")
    private String value;
    @ManyToOne(fetch = FetchType.EAGER)
    private Product product;
}
