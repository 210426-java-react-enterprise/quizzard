package com.revature.quizzard.web.controllers;

import com.revature.quizzard.web.dtos.AppUserDTO;
import com.revature.quizzard.models.AppUser;
import com.revature.quizzard.services.UserService;
import com.revature.quizzard.web.security.Secured;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
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

    @Secured(allowedRoles = {"ADMIN"})
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<AppUserDTO> searchUsers(@RequestParam Map<String, String> requestParams, HttpServletResponse resp) {
        List<AppUserDTO> users = userService.searchUsers(requestParams)
                                            .stream()
                                            .map(AppUserDTO::new)
                                            .collect(Collectors.toList());

        resp.setHeader("Cache-Control", "max-age=3600, must-revalidate");
        return users;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public AppUserDTO registerNewUser(@RequestBody AppUser newUser, HttpServletResponse resp) {
        AppUserDTO registeredUser = new AppUserDTO(userService.register(newUser));
        resp.setHeader("Cache-Control", "no-store");
        return registeredUser;
    }

}
