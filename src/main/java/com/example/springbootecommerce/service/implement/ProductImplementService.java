package com.example.springbootecommerce.service.implement;

import com.example.springbootecommerce.exception.UserNotFoundException;
import com.example.springbootecommerce.pojo.entity.*;
import com.example.springbootecommerce.pojo.requests.ProductInformationRequest;
import com.example.springbootecommerce.pojo.requests.ProductRequest;
import com.example.springbootecommerce.pojo.requests.ProductUpdateRequest;
import com.example.springbootecommerce.repository.ProductInformationRepository;
import com.example.springbootecommerce.repository.ProductRepository;
import com.example.springbootecommerce.repository.ShopRepository;
import com.example.springbootecommerce.repository.TypeRepository;
import com.example.springbootecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class ProductImplementService implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private TypeRepository typeRepository;
    @Autowired
    private ProductInformationRepository productInformationRepository;
    @Override
    public Product saveProduct(ProductRequest productRequest) {
        Product product = new Product();

        Shop shop = shopRepository.findShopsById(productRequest.getId_shop());
        if(shop == null){
            throw new RuntimeException("Cannot find shop by id_shop: "+
                                productRequest.getId_shop());
        }product.setShop(shop);

        Type type = typeRepository.findTypeById(productRequest.getId_type());
        if(type == null){
            throw new RuntimeException("Cannot find type by id_type: "+
                    productRequest.getId_type());
        }
        product.setType(type);

        ProductInformation productInformation = productInformationRepository.findProductInformationById(productRequest.getProduct_info_id());
        if(productInformation == null){
            throw new RuntimeException("Cannot find product_info by id_type: "+
                    productRequest.getProduct_info_id());
        }
        product.setProductInformation(productInformation);

        product.setTitle(productRequest.getTitle());
        product.setPrice(productRequest.getPrice());
        product.setDescription(productRequest.getDescription());
        product.setQuantity(productRequest.getQuantity());
        product.setCreatedAt(new Date());

        return productRepository.save(product);
    }

    @Override
    public void deleteProductById(Long id) {
        Product product = productRepository.findProductById(id);
        if(product == null){
            throw new RuntimeException("Product cannot find by id : "+ id);
        }
        productRepository.delete(product);
    }

    @Override
    public Product updateProduct(ProductUpdateRequest productUpdateRequesttRequestRequest, Long id) {
        Product product = productRepository.findProductById(id);
        if(product == null){
            throw  new RuntimeException("Cannot find product by id : "+id);
        }
        product.setTitle(productUpdateRequesttRequestRequest.getTitle());
        product.setPrice(productUpdateRequesttRequestRequest.getPrice());
        product.setQuantity(productUpdateRequesttRequestRequest.getQuantity());
        product.setDescription(productUpdateRequesttRequestRequest.getDescription());
        return productRepository.save(product);
    }

    @Override
    public List<Product> listAll() {
        List<Product> products = productRepository.findAll();
        return products;
    }

    @Override
    public Product findProductById(Long id) {
        Product product = productRepository.findProductById(id);
        if(product == null){
            throw  new RuntimeException("Cannot find product by id : "+id);
        }
        return product;
    }
}
