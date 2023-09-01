package com.example.springbootecommerce.pojo.requests;

import lombok.Data;

@Data
public class MessageRequest {
    private String name;
    private String phone;
    private String email;
    private String content;
}
