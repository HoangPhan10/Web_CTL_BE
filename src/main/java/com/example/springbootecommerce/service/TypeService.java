package com.example.springbootecommerce.service;

import com.example.springbootecommerce.pojo.entity.Type;
import com.example.springbootecommerce.pojo.requests.TypeRequest;



public interface TypeService {
    Type saveType(TypeRequest typeRequest);
}
