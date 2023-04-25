package com.example.springbootecommerce.service;

import com.example.springbootecommerce.pojo.requests.UserRequest;

import java.io.IOException;
import java.util.List;

public interface UserService {
    List<com.example.springbootecommerce.pojo.entity.User> listAll();
    com.example.springbootecommerce.pojo.entity.User saveUser(UserRequest userDTO) throws IOException;
}
