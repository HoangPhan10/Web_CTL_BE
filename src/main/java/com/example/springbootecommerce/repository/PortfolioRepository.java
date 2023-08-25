package com.example.springbootecommerce.repository;

import com.example.springbootecommerce.pojo.entity.Portfolio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio,Long> {
    Portfolio findPortfolioById(Long id);
    List<Portfolio> findPortfoliosByStatusIs(boolean statusIs);
    Page<Portfolio> findAll(Pageable pageable);
}
