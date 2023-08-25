package com.example.springbootecommerce.pojo.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChildrenPortfolioRequest {
    private String title;
    private Long id_portfolio;
}
