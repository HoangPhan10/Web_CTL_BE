package com.example.springbootecommerce.repository;

import com.example.springbootecommerce.pojo.entity.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecificationRepository extends JpaRepository<Specification,Long> {
    List<Specification> getSpecificationsByProductId(Long id);
    void deleteSpecificationsByProductId(Long id);
}
