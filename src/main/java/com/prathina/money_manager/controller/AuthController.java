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
public ResponseEntity<?> register(@RequestBody User user) {

    List<User> users = userRepository.findByUsername(user.getUsername());

    if (!users.isEmpty()) {
        return ResponseEntity.badRequest().body("Username already exists");
    }

    return ResponseEntity.ok(userRepository.save(user));
}

    @GetMapping("/users")
public List<User> getAllUsers() {
    return userRepository.findAll();
}

@PostMapping("/login")
public ResponseEntity<?> login(@RequestBody User user) {

    List<User> users = userRepository.findByUsername(user.getUsername());

    if (users.isEmpty()) {
        return ResponseEntity.status(401).body("User not found");
    }

    // take first user (avoid duplicate crash)
    User existingUser = users.get(0);

    if (!existingUser.getPassword().equals(user.getPassword())) {
        return ResponseEntity.status(401).body("Wrong password");
    }

    return ResponseEntity.ok(existingUser);
}
}