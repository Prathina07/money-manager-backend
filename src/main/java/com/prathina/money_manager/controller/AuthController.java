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

    User existingUser = userRepository.findByUsername(user.getUsername());

    if (existingUser != null) {
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

    try {
        System.out.println("LOGIN HIT"); // debug

        if (user == null) {
            return ResponseEntity.badRequest().body("User is null");
        }

        System.out.println("Username: " + user.getUsername());
        System.out.println("Password: " + user.getPassword());

        User existingUser = userRepository.findByUsername(user.getUsername());

        System.out.println("DB User: " + existingUser);

        if (existingUser == null) {
            return ResponseEntity.status(401).body("User not found");
        }

        if (!existingUser.getPassword().equals(user.getPassword())) {
            return ResponseEntity.status(401).body("Wrong password");
        }

        return ResponseEntity.ok(existingUser);

    } catch (Exception e) {
        e.printStackTrace(); // 🔥 THIS IS KEY
        return ResponseEntity.status(500).body("ERROR: " + e.getMessage());
    }
}
}