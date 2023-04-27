package com.example.springbootecommerce.pojo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="colors")
@Data
@NoArgsConstructor

public class Color {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length =20)
    private String color;
    @ManyToOne(fetch = FetchType.EAGER)
    private Product product;
}
