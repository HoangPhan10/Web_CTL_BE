package com.example.springbootecommerce.service.implement;

import com.example.springbootecommerce.exception.UserNotFoundException;
import com.example.springbootecommerce.pojo.entity.Image;
import com.example.springbootecommerce.pojo.entity.Product;
import com.example.springbootecommerce.pojo.entity.Specification;
import com.example.springbootecommerce.pojo.entity.Youtube;
import com.example.springbootecommerce.pojo.requests.ProductRequest;
import com.example.springbootecommerce.pojo.requests.SpecificationRequest;
import com.example.springbootecommerce.pojo.responses.ProductPageResponse;
import com.example.springbootecommerce.pojo.responses.ProductResponse;
import com.example.springbootecommerce.repository.ImageRepository;
import com.example.springbootecommerce.repository.ProductRepository;
import com.example.springbootecommerce.repository.SpecificationRepository;
import com.example.springbootecommerce.repository.YoutubeRepository;
import com.example.springbootecommerce.service.ProductService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class ProductImplementService implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private YoutubeRepository youtubeRepository;
    @Autowired
    private SpecificationRepository specificationRepository;
    @Override
    public void createProduct(ProductRequest productRequest) {
        Product product = new Product();
        product.setDescription(productRequest.getDescription());
        product.setUrl(productRequest.getUrl());
        product.setTitle(productRequest.getTitle());
        product.setParent(productRequest.getParent());
        product.setType(productRequest.getType());
        product.setHtml_description(productRequest.getHtml_description());
        Product productSave = productRepository.save(product);
        for(String url: productRequest.getImages()){
            Image image = new Image();
            image.setUrl(url);
            image.setProduct(productSave);
            imageRepository.save(image);
        }
        for(String url: productRequest.getYoutube()){
            Youtube youtube = new Youtube();
            youtube.setUrl(url);
            youtube.setProduct(productSave);
            youtubeRepository.save(youtube);
        }
        for(SpecificationRequest specificationRequest: productRequest.getSpecification()){
            Specification specification = new Specification();
            specification.setKey(specificationRequest.getKey());
            specification.setValue(specificationRequest.getValue());
            specification.setProduct(productSave);
            specificationRepository.save(specification);
        }
    }

    @Override
    public void updateProduct(ProductRequest productRequest, Long id) {
        Product product = productRepository.findProductById(id);
        if(product == null){
            throw new UserNotFoundException("Product not found for id " + id);
        }
        product.setDescription(productRequest.getDescription());
        product.setUrl(productRequest.getUrl());
        product.setTitle(productRequest.getTitle());
        product.setParent(productRequest.getParent());
        product.setType(productRequest.getType());
        product.setHtml_description(productRequest.getHtml_description());
        imageRepository.deleteImagesByProductId(product.getId());
        youtubeRepository.deleteYoutubesByProductId(product.getId());
        specificationRepository.deleteSpecificationsByProductId(product.getId());
        Product productSave = productRepository.save(product);
        for(String url: productRequest.getImages()){
            Image image = new Image();
            image.setUrl(url);
            image.setProduct(productSave);
            imageRepository.save(image);
        }
        for(String url: productRequest.getYoutube()){
            Youtube youtube = new Youtube();
            youtube.setUrl(url);
            youtube.setProduct(productSave);
            youtubeRepository.save(youtube);
        }
        for(SpecificationRequest specificationRequest: productRequest.getSpecification()){
            Specification specification = new Specification();
            specification.setKey(specificationRequest.getKey());
            specification.setValue(specificationRequest.getValue());
            specification.setProduct(productSave);
            specificationRepository.save(specification);
        }
    }

    @Override
    public ProductPageResponse getListProduct(Optional<String> search, int page, int limit) {
        Pageable pageable = PageRequest.of(page-1, limit);
        Page<Product> products = null;
        if(search.isPresent()){
            products = productRepository.findAllByTitleContaining(search.get(),pageable);
        }else {
            products = productRepository.findAll(pageable);
        }
        Integer totalPage = products.getTotalPages();
        Long totalElement = products.getTotalElements();
        List<ProductResponse> productResponses = new ArrayList<>();
        for (Product product : products) {  
            ProductResponse response = new ProductResponse();
            response.setId(product.getId());
            response.setUrl(product.getUrl());
            response.setTitle(product.getTitle());
            response.setParent(product.getParent());
            response.setType(product.getType());
            response.setDescription(product.getDescription());
            response.setHtml_description(product.getHtml_description());
            response.setStatus(product.isStatus());
            List<Image> images = imageRepository.findImagesByProductId(product.getId());
            List<String> imageResponse = new ArrayList<>();
            for (Image image : images){
                imageResponse.add(image.getUrl());
            }
            List<Youtube> youtubes = youtubeRepository.findYoutubesByProductId(product.getId());
            List<String> youtubeResponse = new ArrayList<>();
            for (Youtube youtube : youtubes){
                youtubeResponse.add(youtube.getUrl());
            }
            List<Specification> specifications = specificationRepository.getSpecificationsByProductId(product.getId());
            List<SpecificationRequest> specificationResponse = new ArrayList<>();
            for (Specification specification : specifications){
                SpecificationRequest specificationRequest = new SpecificationRequest();
                specificationRequest.setKey(specification.getKey());
                specificationRequest.setValue(specification.getValue());
                specificationResponse.add(specificationRequest);
            }
            response.setSpecifications(specificationResponse);
            response.setImages(imageResponse);
            response.setLink_video(youtubeResponse);
            productResponses.add(response);
        }
        ProductPageResponse productPageResponse = new ProductPageResponse();
        productPageResponse.setTotal(totalPage);
        productPageResponse.setTotalElement(totalElement);
        productPageResponse.setProductResponses(productResponses);
        return productPageResponse;
    }

    @Override
    public ProductPageResponse getListProductByParent(String parent, int page, int limit) {
        Pageable pageable = PageRequest.of(page-1, limit);
        Page<Product> products = productRepository.findProductsByParent(parent,pageable);
        Integer totalPage = products.getTotalPages();
        Long totalElement = products.getTotalElements();
        List<ProductResponse> productResponses = new ArrayList<>();
        for (Product product : products) {
            ProductResponse response = new ProductResponse();
            response.setId(product.getId());
            response.setUrl(product.getUrl());
            response.setTitle(product.getTitle());
            response.setParent(product.getParent());
            response.setType(product.getType());
            response.setDescription(product.getDescription());
            response.setHtml_description(product.getHtml_description());
            response.setStatus(product.isStatus());
            List<Image> images = imageRepository.findImagesByProductId(product.getId());
            List<String> imageResponse = new ArrayList<>();
            for (Image image : images){
                imageResponse.add(image.getUrl());
            }
            List<Youtube> youtubes = youtubeRepository.findYoutubesByProductId(product.getId());
            List<String> youtubeResponse = new ArrayList<>();
            for (Youtube youtube : youtubes){
                youtubeResponse.add(youtube.getUrl());
            }
            List<Specification> specifications = specificationRepository.getSpecificationsByProductId(product.getId());
            List<SpecificationRequest> specificationResponse = new ArrayList<>();
            for (Specification specification : specifications){
                SpecificationRequest specificationRequest = new SpecificationRequest();
                specificationRequest.setKey(specification.getKey());
                specificationRequest.setValue(specification.getValue());
                specificationResponse.add(specificationRequest);
            }
            response.setSpecifications(specificationResponse);
            response.setImages(imageResponse);
            response.setLink_video(youtubeResponse);
            productResponses.add(response);
        }
        ProductPageResponse productPageResponse = new ProductPageResponse();
        productPageResponse.setTotal(totalPage);
        productPageResponse.setTotalElement(totalElement);
        productPageResponse.setProductResponses(productResponses);
        return productPageResponse;
    }

    @Override
    public ProductPageResponse getListProductByType(String type, int page, int limit) {
        Pageable pageable = PageRequest.of(page-1, limit);
        Page<Product> products = productRepository.findProductsByType(type,pageable);
        Integer totalPage = products.getTotalPages();
        Long totalElement = products.getTotalElements();
        List<ProductResponse> productResponses = new ArrayList<>();
        for (Product product : products) {
            ProductResponse response = new ProductResponse();
            response.setId(product.getId());
            response.setUrl(product.getUrl());
            response.setTitle(product.getTitle());
            response.setParent(product.getParent());
            response.setType(product.getType());
            response.setDescription(product.getDescription());
            response.setHtml_description(product.getHtml_description());
            response.setStatus(product.isStatus());
            List<Image> images = imageRepository.findImagesByProductId(product.getId());
            List<String> imageResponse = new ArrayList<>();
            for (Image image : images){
                imageResponse.add(image.getUrl());
            }
            List<Youtube> youtubes = youtubeRepository.findYoutubesByProductId(product.getId());
            List<String> youtubeResponse = new ArrayList<>();
            for (Youtube youtube : youtubes){
                youtubeResponse.add(youtube.getUrl());
            }
            List<Specification> specifications = specificationRepository.getSpecificationsByProductId(product.getId());
            List<SpecificationRequest> specificationResponse = new ArrayList<>();
            for (Specification specification : specifications){
                SpecificationRequest specificationRequest = new SpecificationRequest();
                specificationRequest.setKey(specification.getKey());
                specificationRequest.setValue(specification.getValue());
                specificationResponse.add(specificationRequest);
            }
            response.setSpecifications(specificationResponse);
            response.setImages(imageResponse);
            response.setLink_video(youtubeResponse);
            productResponses.add(response);
        }
        ProductPageResponse productPageResponse = new ProductPageResponse();
        productPageResponse.setTotal(totalPage);
        productPageResponse.setTotalElement(totalElement);
        productPageResponse.setProductResponses(productResponses);
        return productPageResponse;
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findProductById(id);
        if(product == null) {
            throw new UserNotFoundException("Product not found for id " + id);
        }
        product.setStatus(false);
        productRepository.save(product);
    }
    @Override
    public ProductResponse getProductById(Long id) {
        Product product = productRepository.findProductById(id);
        if(product == null) {
            throw new UserNotFoundException("Product not found for id " + id);
        }
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setUrl(product.getUrl());
        response.setTitle(product.getTitle());
        response.setParent(product.getParent());
        response.setType(product.getType());
        response.setDescription(product.getDescription());
        response.setHtml_description(product.getHtml_description());
        response.setStatus(product.isStatus());
        List<Image> images = imageRepository.findImagesByProductId(product.getId());
        List<String> imageResponse = new ArrayList<>();
        for (Image image : images){
            imageResponse.add(image.getUrl());
        }
        List<Youtube> youtubes = youtubeRepository.findYoutubesByProductId(product.getId());
        List<String> youtubeResponse = new ArrayList<>();
        for (Youtube youtube : youtubes){
            youtubeResponse.add(youtube.getUrl());
        }
        List<Specification> specifications = specificationRepository.getSpecificationsByProductId(product.getId());
        List<SpecificationRequest> specificationResponse = new ArrayList<>();
        for (Specification specification : specifications){
            SpecificationRequest specificationRequest = new SpecificationRequest();
            specificationRequest.setKey(specification.getKey());
            specificationRequest.setValue(specification.getValue());
            specificationResponse.add(specificationRequest);
        }
        response.setSpecifications(specificationResponse);
        response.setImages(imageResponse);
        response.setLink_video(youtubeResponse);
        return response;
    }
}
