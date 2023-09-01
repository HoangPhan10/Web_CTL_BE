package com.example.springbootecommerce.service;

import com.example.springbootecommerce.pojo.requests.ProductRequest;
import com.example.springbootecommerce.pojo.responses.ProductPageResponse;
import com.example.springbootecommerce.pojo.responses.ProductResponse;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    void createProduct(ProductRequest productRequest);
    void updateProduct(ProductRequest productRequest, Long id);
    ProductPageResponse getListProduct(Optional<String> search, int page, int limit);
    ProductPageResponse getListProductByParent(String parent, int page, int limit);
    ProductPageResponse getListProductByType(String type, int page, int limit);
    void deleteProduct(Long id);
    ProductResponse getProductById(Long id);
}
