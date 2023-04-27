package com.example.springbootecommerce.controller;

import com.example.springbootecommerce.pojo.entity.Color;
import com.example.springbootecommerce.pojo.entity.Image;
import com.example.springbootecommerce.pojo.entity.Size;
import com.example.springbootecommerce.pojo.entity.User;
import com.example.springbootecommerce.pojo.requests.*;
import com.example.springbootecommerce.pojo.responses.NotiResponse;
import com.example.springbootecommerce.pojo.responses.ObjectResponse;
import com.example.springbootecommerce.service.ColorService;
import com.example.springbootecommerce.service.ImageService;
import com.example.springbootecommerce.service.SizeService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@CrossOrigin(maxAge = 3600)
@RequestMapping("/product_detail")
public class ProductDetailController {
    @Autowired
    private ColorService colorService;
    @Autowired
    private SizeService sizeService;
    @Autowired
    private ImageService imageService;

    @PostMapping("/create_size")
    public ResponseEntity<ObjectResponse> createSize(@Valid @RequestBody SizeRequest sizeRequest){
        Size size = sizeService.createSize(sizeRequest);
        return ResponseEntity.status(200).body(
                new ObjectResponse(HttpStatus.OK, "Create Size successfully", size)
        );
    }
    @DeleteMapping("/delete_size")
    public ResponseEntity<NotiResponse> deleteSize(@RequestParam("id") Long id){
        sizeService.deleteSize(id);
        return ResponseEntity.status(200).body(
                new NotiResponse(HttpStatus.OK, "Delete Size successfully")
        );
    }
    @GetMapping("/sizes")
    public ResponseEntity<ObjectResponse> getListSize(){
        List<Size> sizes = sizeService.listSizeAll();
        return ResponseEntity.status(200).body(
                new ObjectResponse(HttpStatus.OK, "Query list size successfully", sizes)
        );
    }
    @GetMapping("/product_size")
    public ResponseEntity<ObjectResponse> getSizeByProductId(@RequestParam("id") Long id){
        List<Size> sizes = sizeService.listSizeByProductID(id);
        return ResponseEntity.status(200).body(
                new ObjectResponse(HttpStatus.OK, "Query list size successfully", sizes)
        );
    }

    @PostMapping("/create_color")
    public ResponseEntity<ObjectResponse> createColor(@Valid @RequestBody ColorRequest colorRequest){
        Color color = colorService.createColor(colorRequest);
        return ResponseEntity.status(200).body(
                new ObjectResponse(HttpStatus.OK, "Create color successfully", color)
        );
    }
    @GetMapping("/colors")
    public ResponseEntity<ObjectResponse> getListColor(){
        List<Color> colors = colorService.listColorAll();
        return ResponseEntity.status(200).body(
                new ObjectResponse(HttpStatus.OK, "Query list color successfully", colors)
        );
    }
    @DeleteMapping("/delete_color")
    public ResponseEntity<NotiResponse> deleteColor(@RequestParam("id") Long id){
        colorService.deleteColor(id);
        return ResponseEntity.status(200).body(
                new NotiResponse(HttpStatus.OK, "Delete color successfully")
        );
    }
    @GetMapping("/product_color")
    public ResponseEntity<ObjectResponse> getColorByProductId(@RequestParam("id") Long id){
        List<Color> colors = colorService.listColorByProductID(id);
        return ResponseEntity.status(200).body(
                new ObjectResponse(HttpStatus.OK, "Query list color successfully", colors)
        );
    }

    @PostMapping("/create_image")
    public ResponseEntity<ObjectResponse> createImage(@Valid @RequestBody ImageRequest imageRequest){
        Image image = imageService.createImage(imageRequest);
        return ResponseEntity.status(200).body(
                new ObjectResponse(HttpStatus.OK, "Create image successfully", image)
        );
    }

    @GetMapping("/images")
    public ResponseEntity<ObjectResponse> getListImage(){
        List<Image> images = imageService.listImageAll();
        return ResponseEntity.status(200).body(
                new ObjectResponse(HttpStatus.OK, "Query list image successfully", images)
        );
    }
    @DeleteMapping("/delete_image")
    public ResponseEntity<NotiResponse> deleteImage(@RequestParam("id") Long id){
        imageService.deleteImage(id);
        return ResponseEntity.status(200).body(
                new NotiResponse(HttpStatus.OK, "Delete image successfully")
        );
    }
    @PostMapping("/update_image")
    public ResponseEntity<ObjectResponse> updateImage(@Valid @RequestBody ImageUpdateRequest imageUpdateRequest, @RequestParam("id") Long id){
        Image image = imageService.updateImage(imageUpdateRequest, id);
        return ResponseEntity.status(200).body(
                new ObjectResponse(HttpStatus.OK, "update image successfully", image)
        );
    }

    @GetMapping("/product_image")
    public ResponseEntity<ObjectResponse> getImageByProductId(@RequestParam("id") Long id){
        List<Image> images = imageService.listImageByProductID(id);
        return ResponseEntity.status(200).body(
                new ObjectResponse(HttpStatus.OK, "Query list image successfully", images)
        );
    }
}
