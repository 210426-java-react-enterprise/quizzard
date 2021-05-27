package com.revature.quizzard.controllers;

import com.revature.quizzard.dtos.AppUserDTO;
import com.revature.quizzard.dtos.AppUserList;
import com.revature.quizzard.models.AppUser;
import com.revature.quizzard.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.*;

import java.util.List;

@RestController // implies @Controller at the class-level and @ResponseBody for all return types
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "users.xml",produces = MediaType.APPLICATION_XML_VALUE)
    public AppUserList getAllUsers_xml() {
        return userService.getAllUsers_xml();
    }

    @GetMapping(value = "users.json",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AppUser> getAllUsers_json() {
        return userService.getAllUsers_json();
    }

    @GetMapping(value = "users.xml/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public AppUserDTO getUserById_xml(@PathVariable int id) {
        return userService.getUserById_xml(id);
    }

    @GetMapping(value = "users.json/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public AppUser getUserById_json(@PathVariable int id) {
        return userService.getUserById_json(id);
    }

    @GetMapping(value = "users.json/{username, password}", produces = MediaType.APPLICATION_JSON_VALUE)
    public AppUser authenticate_json(@PathVariable String username, String password) {
        return userService.authenticate_json(username, password);
    }

    //    @RequestMapping(method = RequestMethod.POST) <---- old way of mapping prior to Spring v3
    @PostMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public AppUser register(@RequestBody AppUser newUser) {
        return userService.createNewUser(newUser);
    }

    @PatchMapping(value = "/users", produces = APPLICATION_JSON_VALUE)
    public AppUser updateEmail(@RequestParam int id, @RequestParam String newEmail) {
        return userService.updateUserEmail(id, newEmail);
    }

}
