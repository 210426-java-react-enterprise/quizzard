package com.revature.quizzard.controllers;

import com.revature.quizzard.models.AppUser;
import com.revature.quizzard.repo.UserRepository;
import com.revature.quizzard.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class UserController {

    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(value = "users", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AppUser> getAllUsers_json() { return userRepository.findAllUsers(); }

    @GetMapping(value="ping")
    public String testConnection() { return "Route Working"; }


}
