package com.example.springbootecommerce.service.implement;

import com.example.springbootecommerce.exception.UserNotFoundException;
import com.example.springbootecommerce.pojo.entity.ChildrenPortfolio;
import com.example.springbootecommerce.pojo.entity.Portfolio;
import com.example.springbootecommerce.pojo.requests.ChildrenPortfolioRequest;
import com.example.springbootecommerce.pojo.requests.PortfolioRequest;
import com.example.springbootecommerce.pojo.responses.PortfolioPageResponse;
import com.example.springbootecommerce.pojo.responses.PortfolioResponse;
import com.example.springbootecommerce.repository.ChildrenPortfolioRepository;
import com.example.springbootecommerce.repository.PortfolioRepository;
import com.example.springbootecommerce.service.PortfolioService;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
public class PortfolioImplementService implements PortfolioService {
    @Autowired
    private PortfolioRepository portfolioRepository;
    @Autowired
    private ChildrenPortfolioRepository childrenPortfolioRepository;
    @Override
    public ChildrenPortfolio createChildPortfolio(ChildrenPortfolioRequest request) {
        ChildrenPortfolio childrenPortfolio = new ChildrenPortfolio();
        Portfolio portfolio = portfolioRepository.findPortfolioById(request.getId_portfolio());
        childrenPortfolio.setTitle(request.getTitle());
        childrenPortfolio.setPortfolio(portfolio);
        return childrenPortfolioRepository.save(childrenPortfolio);
    }

    @Override
    public ChildrenPortfolio updateChildPortfolio(ChildrenPortfolioRequest request, Long id) {
        ChildrenPortfolio childrenPortfolio = childrenPortfolioRepository.getChildrenPortfolioById(id);
        if (childrenPortfolio == null) {
            throw new UserNotFoundException("Not found child portfolio for " + id);
        }
        Portfolio portfolio = portfolioRepository.findPortfolioById(childrenPortfolio.getPortfolio().getId());
        childrenPortfolio.setTitle(request.getTitle());
        childrenPortfolio.setPortfolio(portfolio);
        return childrenPortfolioRepository.save(childrenPortfolio);
    }

    @Override
    public Portfolio createPortfolio(PortfolioRequest portfolioRequest) {
        Portfolio portfolio = new Portfolio();
        portfolio.setDescription(portfolioRequest.getDescription());
        portfolio.setUrl(portfolioRequest.getUrl());
        portfolio.setTitle(portfolioRequest.getTitle());
        return portfolioRepository.save(portfolio);
    }

    @Override
    public List<PortfolioResponse> getAllPortfolio() {
        List<Portfolio> portfolios = portfolioRepository.findPortfoliosByStatusIs(true);
        List<PortfolioResponse> portfolioResponses = new ArrayList<>();
        for(Portfolio portfolio : portfolios){
            PortfolioResponse portfolioResponse = new PortfolioResponse();
            List<ChildrenPortfolio> childrenPortfolios =
                    childrenPortfolioRepository.getChildrenPortfolioByPortfolioId(portfolio.getId());
            portfolioResponse.setChildren(childrenPortfolios);
            portfolioResponse.setId(portfolio.getId());
            portfolioResponse.setTitle(portfolio.getTitle());
            portfolioResponse.setStatus(portfolio.isStatus());
            portfolioResponse.setUrl(portfolio.getUrl());
            portfolioResponse.setDescription(portfolio.getDescription());
            portfolioResponses.add(portfolioResponse);
        }
        return portfolioResponses;
    }

    @Override
    public PortfolioPageResponse getAllPortfolioPage(int page, int limit) {
        Pageable pageable = PageRequest.of(page-1, limit);
        Page<Portfolio> portfolios = portfolioRepository.findAll(pageable);
        Integer totalPage = portfolios.getTotalPages();
        Long totalElement = portfolios.getTotalElements();
        List<PortfolioResponse> portfolioResponses = new ArrayList<>();
        for(Portfolio portfolio : portfolios){
            PortfolioResponse portfolioResponse = new PortfolioResponse();
            List<ChildrenPortfolio> childrenPortfolios =
                    childrenPortfolioRepository.getChildrenPortfolioByPortfolioId(portfolio.getId());
            portfolioResponse.setChildren(childrenPortfolios);
            portfolioResponse.setId(portfolio.getId());
            portfolioResponse.setTitle(portfolio.getTitle());
            portfolioResponse.setStatus(portfolio.isStatus());
            portfolioResponse.setUrl(portfolio.getUrl());
            portfolioResponse.setDescription(portfolio.getDescription());
            portfolioResponses.add(portfolioResponse);
        }
        PortfolioPageResponse portfolioPageResponse = new PortfolioPageResponse(portfolioResponses,totalPage,totalElement);
        return portfolioPageResponse;
    }

    @Override
    public Portfolio updatePortfolio(PortfolioRequest portfolioRequest,Long id) {
        Portfolio portfolio = portfolioRepository.findPortfolioById(id);
        if(portfolio == null){
            throw new UserNotFoundException("Not found portfolio with id " + id);
        }
        portfolio.setTitle(portfolioRequest.getTitle());
        portfolio.setDescription(portfolioRequest.getDescription());
        if(portfolioRequest.getUrl() != null) {
            portfolio.setUrl(portfolioRequest.getUrl());
        }
        return portfolioRepository.save(portfolio);
    }

    @Override
    public void deletePortfolioById(Long id) {
        Portfolio portfolio = portfolioRepository.findPortfolioById(id);
        if(portfolio == null){
            throw new UserNotFoundException("Not found portfolio with id " + id);
        }
        portfolio.setStatus(false);
        portfolioRepository.save(portfolio);
    }

    @Override
    public void deletePortfolioChildrenById(Long id) {
        ChildrenPortfolio children = childrenPortfolioRepository.getChildrenPortfolioById(id);
        if (children == null){
           throw new UserNotFoundException("Not found portfolio children with id "+ id);
        }
        childrenPortfolioRepository.deleteById(id);
    }

    @Override
    public List<ChildrenPortfolio> getChildrenPortfoliosByIdPortfolio(Long id) {
        List<ChildrenPortfolio> childrenPortfolios =
                childrenPortfolioRepository.getChildrenPortfolioByPortfolioId(id);
        return childrenPortfolios;
    }
}
