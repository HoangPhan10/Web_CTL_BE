package com.example.springbootecommerce.repository;

import com.example.springbootecommerce.pojo.entity.Product;
import com.example.springbootecommerce.pojo.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;


@EnableJpaRepositories
@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    Product findProductById(Long id);
}
