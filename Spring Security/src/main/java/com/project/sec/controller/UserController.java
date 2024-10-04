package com.project.sec.controller;

import com.project.sec.model.Users;
import com.project.sec.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<Users> register(@RequestBody @Valid Users user) {
        return new ResponseEntity<>(
            userService.registerUser(user), CREATED
        );
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid Users user) {
        boolean verified = userService.verifyUser(user);

        if (!verified) {
            return new ResponseEntity<>("Login Failed", UNAUTHORIZED);
        }
        return new ResponseEntity<>("Login Successful", OK);
    }
}
