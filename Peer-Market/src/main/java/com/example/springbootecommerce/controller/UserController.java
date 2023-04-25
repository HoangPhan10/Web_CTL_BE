package com.example.springbootecommerce.controller;

import com.example.springbootecommerce.pojo.entity.User;
import com.example.springbootecommerce.pojo.requests.UserRequest;
import com.example.springbootecommerce.pojo.responses.ObjectResponse;
import com.example.springbootecommerce.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@Slf4j
@CrossOrigin(maxAge = 3600)
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/save")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ObjectResponse> saveUser(@Valid @RequestBody UserRequest userDTO) throws IOException {
        User user = userService.saveUser(userDTO);
        return ResponseEntity.status(200).body(
                new ObjectResponse(HttpStatus.OK,"Create user successfully",user)
        );
    }

    @GetMapping("")
    public ResponseEntity<ObjectResponse> getListUsers(){
        List<User> users = userService.listAll();
        return ResponseEntity.status(200).body(
                new ObjectResponse(HttpStatus.OK,"Query list user successfully",users)
        );
    }
}
