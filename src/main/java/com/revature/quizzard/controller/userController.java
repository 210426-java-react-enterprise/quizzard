package com.revature.quizzard.controller;

import com.revature.quizzard.dtos.AppUserList;
import com.revature.quizzard.repos.UserRepo;
import com.revature.quizzard.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.print.attribute.standard.Media;

@RestController
public class userController {
    
    private UserService userService;
    private UserRepo userRepo;
    @Autowired
    public userController(UserRepo userRepo){
        this.userRepo = userRepo;
    }
    
    @GetMapping(value ="users.json" ,produces =MediaType.APPLICATION_JSON_VALUE )
    public @ResponseBody AppUserList allUsers() {
        
        return (AppUserList) userRepo.findAllUsers();
        
    }
    
}
