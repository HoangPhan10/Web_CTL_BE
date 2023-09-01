package com.example.springbootecommerce.repository;

import com.example.springbootecommerce.pojo.entity.Youtube;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface YoutubeRepository extends JpaRepository<Youtube,Long> {
    List<Youtube> findYoutubesByProductId(Long id);
    void deleteYoutubesByProductId(Long id);
}
