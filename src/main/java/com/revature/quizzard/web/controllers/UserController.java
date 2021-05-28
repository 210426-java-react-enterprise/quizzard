package com.revature.quizzard.web.controllers;

import com.revature.quizzard.dtos.AppUserDTO;
import com.revature.quizzard.exceptions.InvalidRequestException;
import com.revature.quizzard.exceptions.ResourceNotFoundException;
import com.revature.quizzard.models.AppUser;
import com.revature.quizzard.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public List<AppUserDTO> searchUsers(@RequestParam Map<String, String> requestParams) {
        return userService.searchUsers(requestParams).stream().map(AppUserDTO::new).collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public AppUserDTO registerNewUser(@RequestBody AppUser newUser) {
        return new AppUserDTO(userService.register(newUser));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public void handleResourceNotFoundException() { }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidRequestException.class)
    public void handleInvalidRequestException() { }

}
