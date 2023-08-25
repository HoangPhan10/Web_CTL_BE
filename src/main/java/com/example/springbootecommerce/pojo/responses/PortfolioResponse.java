package com.example.springbootecommerce.pojo.responses;

import com.example.springbootecommerce.pojo.entity.ChildrenPortfolio;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PortfolioResponse {
    private Long id;
    private String title;
    private boolean status;
    private String description;
    private String url;
    private List<ChildrenPortfolio> children;
}
