package com.example.springbootecommerce.repository;

import com.example.springbootecommerce.pojo.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image,Long> {
    List<Image> findImagesByProductId(Long id);
    void deleteImagesByProductId(Long id);
}
