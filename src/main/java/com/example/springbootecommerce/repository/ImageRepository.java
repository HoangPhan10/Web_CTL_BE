package com.example.springbootecommerce.repository;

import com.example.springbootecommerce.pojo.entity.Image;
import com.example.springbootecommerce.pojo.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image,Long> {
    Image findImageById(Long id);
}
