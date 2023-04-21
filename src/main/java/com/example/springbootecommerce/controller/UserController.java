package com.example.springbootecommerce.controller;

import com.example.springbootecommerce.pojo.entity.User;
import com.example.springbootecommerce.pojo.requests.UserRequest;
import com.example.springbootecommerce.pojo.responses.ObjectResponse;
import com.example.springbootecommerce.service.UserService;
import com.example.springbootecommerce.service.implement.UserImplementService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.xmlbeans.impl.xb.xsdschema.Attribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@CrossOrigin(maxAge = 3600)
@RequestMapping(path = "/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("")
    List<User> getAllUser(){
        return userService.listAll();
    }
    @GetMapping("/{id}")
    ResponseEntity<ObjectResponse> findById(@PathVariable Long id){
        Optional<User> findUser = userService.findById(id);
        return findUser.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ObjectResponse(HttpStatus.OK, "Query user successfully", findUser)
                ):
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ObjectResponse(HttpStatus.NOT_FOUND, "Cannot find product with Id = "+ id, "")
                );
    }
    @PostMapping("/save")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ObjectResponse> saveUser(@Valid @RequestBody User userDTO) throws IOException {
        com.example.springbootecommerce.pojo.entity.User user = userService.saveUser(userDTO);
        return ResponseEntity.status(200).body(
                new ObjectResponse(HttpStatus.OK, "Add user successfully", user)
        );
    }

    @PutMapping("/update")
    ResponseEntity<ObjectResponse> updateUser(@Valid @RequestBody User userDTO , Long id){
        User updateUser = userService.findById(id)
                .map(user -> {
                    user.setEmail(userDTO.getEmail());
                    user.setName(userDTO.getName());
                    user.setUsername(userDTO.getUsername());
                    user.setPhoto(userDTO.getPhoto());
                    user.setCard(userDTO.getCard());
                    user.setRole(userDTO.getRole());
                    user.setPassword(userDTO.getPassword());
                    user.setUpdatedAt(new Date());
                    //
                    try {
                        return userService.saveUser(user);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }).orElseGet(() -> {
                    try {
                        return  userService.saveUser(userDTO);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
        return ResponseEntity.status(HttpStatus.OK).body(
                new ObjectResponse(HttpStatus.OK, "Update Product successfully", updateUser)
        );
    }
    @DeleteMapping("/delete")
    ResponseEntity<ObjectResponse> deleteProduct(@PathVariable Long id) {
        boolean exists = userService.existsById(id);
        if (exists) {
            userService.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ObjectResponse(HttpStatus.OK, "Delete user successfully", "")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ObjectResponse(HttpStatus.NOT_FOUND, "Cannot find user to delete", "")
        );
    }

}
