package com.example.springbootecommerce.service;

import com.example.springbootecommerce.pojo.entity.User;
import com.example.springbootecommerce.pojo.requests.UserRequest;

import java.io.IOException;
import java.util.List;

public interface UserService {
    List<User> listAll();
    User saveUser(UserRequest userDTO) throws IOException;
    User getUserByJWT(String jwt) throws IOException;
}
