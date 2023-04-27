package com.example.springbootecommerce.pojo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
<<<<<<< HEAD
@Table(name = "products")
=======
@Table(name = "product")
>>>>>>> 92f7544 ([ADD] api address)
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length =100)
    private String title;

<<<<<<< HEAD

=======
>>>>>>> 92f7544 ([ADD] api address)
    @Column(nullable = false, length =20)
    private Float price;

    @Column(nullable = false, length =50)
    private String description;

    @Column(nullable = false, length =20)
    private long quantity;
    private Date createdAt;
    private Date updatedAt;

<<<<<<< HEAD
    @ManyToOne(fetch = FetchType.EAGER)
    private Shop shop;

    @ManyToOne(fetch = FetchType.EAGER)
    private Type type;
=======
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private User user;
>>>>>>> 92f7544 ([ADD] api address)

}
