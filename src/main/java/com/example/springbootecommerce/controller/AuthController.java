package com.example.springbootecommerce.controller;

import com.example.springbootecommerce.pojo.requests.LoginRequest;
import com.example.springbootecommerce.pojo.responses.JWTResponse;
import com.example.springbootecommerce.pojo.responses.ObjectResponse;
import com.example.springbootecommerce.security.JWTGenerator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(maxAge = 3600)

public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTGenerator jwtGenerator;
    @PostMapping("/login")
    public ResponseEntity<ObjectResponse> login(@RequestBody @Valid LoginRequest loginDTO){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(),loginDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return ResponseEntity.status(200).body(
                new ObjectResponse(HttpStatus.OK,"Login successfully",new JWTResponse(token))
        );
    }

}
