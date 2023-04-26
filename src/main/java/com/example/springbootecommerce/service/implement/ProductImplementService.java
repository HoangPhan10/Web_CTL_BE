package com.example.springbootecommerce.service.implement;

import com.example.springbootecommerce.exception.UserNotFoundException;
import com.example.springbootecommerce.pojo.entity.Product;
import com.example.springbootecommerce.pojo.entity.Shop;
import com.example.springbootecommerce.pojo.entity.Type;
import com.example.springbootecommerce.pojo.entity.User;
import com.example.springbootecommerce.pojo.requests.ProductRequest;
import com.example.springbootecommerce.pojo.requests.ProductUpdateRequest;
import com.example.springbootecommerce.repository.ProductRepository;
import com.example.springbootecommerce.repository.ShopRepository;
import com.example.springbootecommerce.repository.TypeRepository;
import com.example.springbootecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class ProductImplementService implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private TypeRepository typeRepository;
    @Override
    public Product saveProduct(ProductRequest productRequest) {
        Product product = new Product();

        Shop shop = shopRepository.findShopsById(productRequest.getId_shop());
        if(shop == null){
            throw new RuntimeException("Cannot find shop bt id_shop: "+
                                productRequest.getId_shop());
        }
        Type type = typeRepository.findTypeById(productRequest.getId_type());
        if(type == null){
            throw new RuntimeException("Cannot find type bt id_type: "+
                    productRequest.getId_type());
        }
        product.setShop(shop);
        product.setType(type);

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
            throw new RuntimeException("Product connot find by id : "+ id);
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
}
