package com.prathina.money_manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.prathina.money_manager.model.User;
import com.prathina.money_manager.repository.UserRepository;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    // REGISTER
    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userRepository.save(user);
    }
    @GetMapping("/users")
public List<User> getAllUsers() {
    return userRepository.findAll();
}

@PostMapping("/login")
public ResponseEntity<?> login(@RequestBody User user) {

    if (user.getUsername() == null || user.getPassword() == null) {
        return ResponseEntity.badRequest().body("Username or password missing");
    }

    User existingUser = userRepository.findByUsername(user.getUsername());

    if (existingUser == null) {
        return ResponseEntity.status(401).body("User not found");
    }

    if (existingUser.getPassword() == null ||
        !existingUser.getPassword().equals(user.getPassword())) {
        return ResponseEntity.status(401).body("Wrong password");
    }

    return ResponseEntity.ok(existingUser);
}
}