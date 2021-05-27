package com.revature.quizzard.web.controllers;

import com.revature.quizzard.models.AppUser;
import com.revature.quizzard.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.*;

@RestController
@RequestMapping("users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<AppUser> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(value = "{id}", produces = APPLICATION_JSON_VALUE)
    public AppUser getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

}
