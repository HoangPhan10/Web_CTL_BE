package com.example.springbootecommerce.service.implement;

import com.example.springbootecommerce.exception.RoleNotFoundException;
import com.example.springbootecommerce.pojo.entity.Role;
import com.example.springbootecommerce.pojo.entity.User;
import com.example.springbootecommerce.pojo.requests.UserRequest;
import com.example.springbootecommerce.repository.RoleRepository;
import com.example.springbootecommerce.repository.UserRepository;
import com.example.springbootecommerce.security.JWTGenerator;
import com.example.springbootecommerce.service.UserService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Slf4j
@Transactional
public class UserImplementService implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public List<com.example.springbootecommerce.pojo.entity.User> listAll() {
        List<com.example.springbootecommerce.pojo.entity.User> users = userRepository.findAll();
        return users;
    }
    @Override
    public com.example.springbootecommerce.pojo.entity.User saveUser(UserRequest userDTO){
        User user = new User();
        String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setPassword(encodedPassword);
        user.setUsername(userDTO.getUsername());
        Role role_user = roleRepository.findRoleByName(userDTO.getRole());
        if(role_user == null){
            throw new RoleNotFoundException("Role not found with id ");
        }
        user.setRole(role_user);
        userRepository.save(user);
        return user;
    }

    @Override
    public User getUserByJWT(String jwt) throws IOException {
        JWTGenerator jwtGenerator = new JWTGenerator();
        String email = jwtGenerator.getUsernameFromJWT(jwt);
        AtomicReference<User> user = new AtomicReference<>(userRepository.getUserByEmail(email));
        return user.get();
    }
}
