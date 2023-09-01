package com.example.springbootecommerce.pojo.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    private String description;
    private String html_description;
    private List<String> images;
    private String parent;
    private String title;
    private String url;
    private String type;
    private List<String> youtube;
    private List<SpecificationRequest> specification;
}
