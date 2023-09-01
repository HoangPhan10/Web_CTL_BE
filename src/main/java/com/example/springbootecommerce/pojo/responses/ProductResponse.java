package com.example.springbootecommerce.pojo.responses;

import com.example.springbootecommerce.pojo.requests.SpecificationRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private Long id;
    private String url;
    private String title;
    private String parent;
    private String type;
    private String description;
    private Boolean status;
    private List<String> images;
    private String html_description;
    private List<String> link_video;
    private List<SpecificationRequest> specifications;
}
