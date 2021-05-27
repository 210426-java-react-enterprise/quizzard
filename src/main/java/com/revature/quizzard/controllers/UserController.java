package com.revature.quizzard.controllers;

import com.revature.quizzard.models.AppUser;
import com.revature.quizzard.repos.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.*;

@RestController
public class UserController {
    private AppUserRepository userRepo;

    @Autowired
    public UserController(AppUserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping(value = "users.json", produces = APPLICATION_JSON_VALUE)
    public List<AppUser> getAllUsers() {
        return userRepo.findAllUsers();
    }
}
