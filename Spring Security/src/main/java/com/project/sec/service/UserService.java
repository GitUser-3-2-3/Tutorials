package com.project.sec.service;

import com.project.sec.model.Users;
import com.project.sec.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authManager;
    private final JwtService jwtService;

    @Autowired
    public UserService(
        UserRepository userRepository, PasswordEncoder passwordEncoder,
        AuthenticationManager authManager, JwtService jwtService
    ) {
        this.userRepository = userRepository;
        this.authManager = authManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public Users registerUser(final Users user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public boolean verifyUser(Users user) {
        Authentication authentication = authManager.authenticate(
            new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );
        if (authentication.isAuthenticated()) {
            String token = jwtService.generateToken();
        }
        return authentication.isAuthenticated();
    }
}








