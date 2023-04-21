package com.example.springbootecommerce.service;

import com.example.springbootecommerce.pojo.entity.User;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface UserService {
    List<com.example.springbootecommerce.pojo.entity.User> listAll();
    com.example.springbootecommerce.pojo.entity.User saveUser(User userDTO) throws IOException;

    boolean existsById(Long id);

    void deleteById(Long id);

    Optional<User> findById(Long id);
}
