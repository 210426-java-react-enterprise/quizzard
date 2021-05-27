package com.revature.quizzard.controllers;

import com.revature.quizzard.dtos.Credentials;
import com.revature.quizzard.exceptions.AuthenticationException;
import com.revature.quizzard.exceptions.InvalidRequestException;
import com.revature.quizzard.models.AppUser;
import com.revature.quizzard.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.springframework.http.MediaType.*;

@RestController
@RequestMapping("/users")
public class AppUserController {

    private UserService userService;

    @Autowired
    public AppUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "{id}", produces = APPLICATION_JSON_VALUE)
    public AppUser getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @PostMapping(value = "/register", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public AppUser saveUser(@RequestBody AppUser user, HttpServletResponse resp) {
        try {
            return userService.saveUser(user);
        } catch (InvalidRequestException e) {
            resp.setStatus(400);
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping(value = "auth", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public AppUser authenticate(@RequestBody Credentials creds, HttpServletResponse resp, HttpServletRequest req) {
        try {
            AppUser user = userService.authenticate(creds.getUsername(), creds.getPassword());
            req.getSession().setAttribute("this-user", user);
            return user;
        } catch (AuthenticationException e) {
            resp.setStatus(401);
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping(value = "getall", produces = APPLICATION_JSON_VALUE)
    public List<AppUser> getAllUsers(HttpServletRequest req, HttpServletResponse resp) {
        try {
            AppUser loggedInUser = (AppUser) req.getSession().getAttribute("this-user");
            return userService.getAllUsers(loggedInUser);
        } catch (AuthenticationException e) {
            resp.setStatus(403);
            e.printStackTrace();
            return null;
        }
    }
}
