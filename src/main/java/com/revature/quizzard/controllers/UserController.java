package com.revature.quizzard.controllers;

import com.revature.quizzard.models.*;
import com.revature.quizzard.services.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){this.userService = userService;}

    @GetMapping(value = "users", produces = MediaType.APPLICATION_JSON_VALUE)
    public /*@ResponseBody*/ List<AppUser> getAllUsers_json() {return userService.getAllUsers();}

    @PostMapping(value = "users", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public AppUser postUsers_json(@RequestBody AppUser newUser){return userService.register(newUser);}


}
