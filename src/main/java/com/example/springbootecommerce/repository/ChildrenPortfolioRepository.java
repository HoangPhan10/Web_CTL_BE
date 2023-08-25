package com.example.springbootecommerce.repository;

import com.example.springbootecommerce.pojo.entity.ChildrenPortfolio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChildrenPortfolioRepository extends JpaRepository<ChildrenPortfolio,Long> {
    List<ChildrenPortfolio> getChildrenPortfolioByPortfolioId(Long id);
    ChildrenPortfolio getChildrenPortfolioById(Long id);
}
