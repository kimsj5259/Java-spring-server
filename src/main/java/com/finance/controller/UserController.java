package com.finance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finance.dto.ApiResponse;
import com.finance.model.User;
import com.finance.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ApiResponse signupUser(@RequestBody User userRequest) {
        return userService.registerUser(userRequest);
    }

    @PostMapping("/login")
    public ApiResponse loginUser(@RequestBody User userReqeust) {
        return userService.authenticateUser(userReqeust);
    }

}
