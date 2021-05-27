package com.revature.quizzard.controllers;

import com.revature.quizzard.models.AppUser;
import com.revature.quizzard.services.UserService;
import com.revature.quizzard.services._UserService;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.http.MediaType.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){ this.userService = userService; }

    @PostMapping(value="/users", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public AppUser register(@RequestBody AppUser newUser){ return userService.register(newUser);}

}
