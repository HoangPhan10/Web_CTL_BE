package com.example.springbootecommerce.repository;

import com.example.springbootecommerce.pojo.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAll(Pageable pageable);
    Page<Product> findProductsByParent(String parent, Pageable pageable);
    Page<Product> findProductsByType(String type, Pageable pageable);
    Page<Product> findAllByTitleContaining(String title, Pageable pageable);
    Product findProductById(Long id);
}
