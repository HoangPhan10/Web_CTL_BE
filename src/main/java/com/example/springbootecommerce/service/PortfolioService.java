package com.example.springbootecommerce.service;

import com.example.springbootecommerce.pojo.entity.ChildrenPortfolio;
import com.example.springbootecommerce.pojo.entity.Portfolio;
import com.example.springbootecommerce.pojo.requests.ChildrenPortfolioRequest;
import com.example.springbootecommerce.pojo.requests.PortfolioRequest;
import com.example.springbootecommerce.pojo.responses.PortfolioPageResponse;
import com.example.springbootecommerce.pojo.responses.PortfolioResponse;

import java.util.List;

public interface PortfolioService {
    ChildrenPortfolio createChildPortfolio(ChildrenPortfolioRequest request);
    ChildrenPortfolio updateChildPortfolio(ChildrenPortfolioRequest request, Long id);
    Portfolio createPortfolio(PortfolioRequest portfolioRequest);
    List<PortfolioResponse> getAllPortfolio();
    PortfolioPageResponse getAllPortfolioPage(int page, int limit);
    Portfolio updatePortfolio(PortfolioRequest portfolioRequest,Long id);
    void deletePortfolioById(Long id);
    void deletePortfolioChildrenById(Long id);
}
