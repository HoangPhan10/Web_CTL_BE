package com.example.springbootecommerce.controller;

import com.example.springbootecommerce.pojo.requests.ProductRequest;
import com.example.springbootecommerce.pojo.responses.ObjectResponse;
import com.example.springbootecommerce.pojo.responses.ProductPageResponse;
import com.example.springbootecommerce.pojo.responses.ProductResponse;
import com.example.springbootecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/product")
@CrossOrigin(maxAge = 3600)
public class ProductController {
    @Autowired
    private ProductService productService;
    @PostMapping("/save")
    public ResponseEntity<ObjectResponse> saveProduct(@RequestBody ProductRequest productRequest) {
        productService.createProduct(productRequest);
        return ResponseEntity.ok().body(
                new ObjectResponse(HttpStatus.CREATED,"Create Product successfully",null)
        );
    }
    @PutMapping("/update")
    public ResponseEntity<ObjectResponse> updateProduct(@RequestBody ProductRequest productRequest,@RequestParam("id") Long id) {
        productService.updateProduct(productRequest,id);
        return ResponseEntity.ok().body(
                new ObjectResponse(HttpStatus.CREATED,"Update Product successfully",null)
        );
    }
    @GetMapping("")
    public ResponseEntity<ObjectResponse> getProduct(@RequestParam("s") Optional<String> s, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit ) {
        ProductPageResponse productPageResponse = productService.getListProduct(s,page, limit);
        return ResponseEntity.ok().body(
                new ObjectResponse(HttpStatus.CREATED,"Get List Product successfully",productPageResponse)
        );
    }
    @GetMapping("/parent")
    public ResponseEntity<ObjectResponse> getProductByParent(@RequestParam("parent") String parent, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit ) {
        ProductPageResponse productPageResponse = productService.getListProductByParent(parent,page, limit);
        return ResponseEntity.ok().body(
                new ObjectResponse(HttpStatus.CREATED,"Get List Product successfully",productPageResponse)
        );
    }
    @GetMapping("/type")
    public ResponseEntity<ObjectResponse> getProductByType(@RequestParam("type") String type, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit ) {
        ProductPageResponse productPageResponse = productService.getListProductByType(type,page,limit);
        return ResponseEntity.ok().body(
                new ObjectResponse(HttpStatus.CREATED,"Get List Product successfully",productPageResponse)
        );
    }
    @DeleteMapping("/delete")
    public ResponseEntity<ObjectResponse> deleteProduct(@RequestParam("id") Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().body(
                new ObjectResponse(HttpStatus.CREATED,"Delete Product by id Successfully",null)
        );
    }
    @GetMapping("/get")
    public ResponseEntity<ObjectResponse> getProductById(@RequestParam("id") Long id) {
        ProductResponse productResponse = productService.getProductById(id);
        return ResponseEntity.ok().body(
                new ObjectResponse(HttpStatus.CREATED,"Delete Product by id Successfully",productResponse)
        );
    }
}
