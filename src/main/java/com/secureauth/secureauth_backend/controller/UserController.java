package com.secureauth.secureauth_backend.controller;

import com.secureauth.secureauth_backend.dto.LoginRequest;
import  com.secureauth.secureauth_backend.model.User;
import  com.secureauth.secureauth_backend.repository.UserRepository;
import com.secureauth.secureauth_backend.util.JwtUtil;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

   private final UserRepository userRepository;
private final PasswordEncoder passwordEncoder;
private final JwtUtil jwtUtil;

public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.jwtUtil = jwtUtil;

}


    // 1) Register a new user
   @PostMapping
public User registerUser(@RequestBody User user) {

    // hash password before saving
    user.setPassword(passwordEncoder.encode(user.getPassword()));

    return userRepository.save(user);
}


    // 2) Get all users
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

@GetMapping("/me")
public Object me(@AuthenticationPrincipal UserDetails userDetails) {
    if (userDetails == null) return Map.of("authenticated", false);
    return Map.of(
        "authenticated", true,
        "email", userDetails.getUsername()
    );
}

@PostMapping("/login")

public String login(@RequestBody LoginRequest loginRequest) {

    User user = userRepository.findByEmail(loginRequest.getEmail());
    if (user == null) {
        return "User not found";
    }

    boolean isPasswordMatch = passwordEncoder.matches(
            loginRequest.getPassword(),
            user.getPassword()
    );

    if (!isPasswordMatch) {
        return "Incorrect password";
    }

    // generate JWT
    String token = jwtUtil.generateToken(user.getEmail());

    return "TOKEN : " + token;
}



}
