package com.revature.quizzard.web.controllers;

import com.revature.quizzard.dtos.Credentials;
import com.revature.quizzard.models.AppUser;
import com.revature.quizzard.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public AppUser authenticate(@RequestBody Credentials credentials) {
        AppUser user = userService.authenticate(credentials.getUsername(), credentials.getPassword());
        return user;
    }
}
