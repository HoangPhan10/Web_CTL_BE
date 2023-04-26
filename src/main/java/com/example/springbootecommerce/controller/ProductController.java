package com.example.springbootecommerce.controller;


import com.example.springbootecommerce.pojo.entity.Product;
import com.example.springbootecommerce.pojo.entity.Type;
import com.example.springbootecommerce.pojo.requests.*;
import com.example.springbootecommerce.pojo.responses.NotiResponse;
import com.example.springbootecommerce.pojo.responses.ObjectResponse;
import com.example.springbootecommerce.service.ProductService;
import com.example.springbootecommerce.service.TypeService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.lang.model.element.TypeElement;

@RestController
@Slf4j
@CrossOrigin(maxAge = 3600)
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private TypeService typeService;

    @PostMapping("/save")
    public ResponseEntity<ObjectResponse>  createProduct(@Valid @RequestBody ProductRequest productRequest){
        Product product = productService.saveProduct(productRequest);
        return ResponseEntity.status(200).body(
                new ObjectResponse(HttpStatus.OK, "Create product successfully", product)
        );
    }


    @PostMapping("/type")
    public ResponseEntity<ObjectResponse>  createType(@Valid @RequestBody TypeRequest typeRequest){
        Type type = typeService.saveType(typeRequest);
        return ResponseEntity.status(200).body(
                new ObjectResponse(HttpStatus.OK, "Create type successfully", type)
        );
    }

    @DeleteMapping("/delete")
    public ResponseEntity<NotiResponse> deleteProduct(@RequestParam("id") Long id) {
        productService.deleteProductById(id);
        return ResponseEntity.ok().body(
                new NotiResponse(HttpStatus.OK, "Delete Product successfully")
        );
    }

    @PostMapping("/update")
    public  ResponseEntity<ObjectResponse> updateProduct(@Valid @RequestBody ProductUpdateRequest productUpdateRequest, @RequestParam("id") Long id){
        Product product = productService.updateProduct(productUpdateRequest, id);
        return ResponseEntity.status(200).body(
                new ObjectResponse(HttpStatus.OK, "update product successfully", product)
        );
    }



}
