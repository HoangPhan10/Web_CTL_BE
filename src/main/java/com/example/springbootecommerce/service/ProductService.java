package com.example.springbootecommerce.service;

import com.example.springbootecommerce.pojo.entity.Product;
import com.example.springbootecommerce.pojo.requests.ProductRequest;
import com.example.springbootecommerce.pojo.requests.ProductUpdateRequest;

public interface ProductService {


    Product saveProduct(ProductRequest productRequest);

    void deleteProductById(Long id);

    Product updateProduct(ProductUpdateRequest productUpdateRequest, Long id);
}
