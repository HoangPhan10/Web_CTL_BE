package com.example.springbootecommerce.pojo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="sizes")
@Data
@NoArgsConstructor

public class Size {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length =20)
    private int size;
    @ManyToOne(fetch = FetchType.EAGER)
    private Product product;

}
