package com.revature.quizzard.controllers;

import com.revature.quizzard.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserControler(UserService userService){
        this.userService = userService;
    }

}
