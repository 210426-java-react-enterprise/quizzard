package com.revature.quizzard.web.controllers;

import com.revature.quizzard.web.dtos.AppUserDTO;
import com.revature.quizzard.web.dtos.Credentials;
import com.revature.quizzard.models.AppUser;
import com.revature.quizzard.services.UserService;
import com.revature.quizzard.web.dtos.Principal;
import com.revature.quizzard.web.security.JwtConfig;
import com.revature.quizzard.web.security.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import static org.springframework.http.MediaType.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private UserService userService;
    private TokenGenerator tokenGenerator;
    private JwtConfig jwtConfig;

    @Autowired
    public AuthController(UserService userService, TokenGenerator tokenGenerator, JwtConfig jwtConfig) {
        this.userService = userService;
        this.tokenGenerator = tokenGenerator;
        this.jwtConfig = jwtConfig;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public Principal authenticate(@RequestBody @Valid Credentials credentials, HttpServletResponse resp) {
        Principal user = userService.authenticate(credentials.getUsername(), credentials.getPassword());
        String jwt = tokenGenerator.createJwt(user);
        resp.setHeader(jwtConfig.getHeader(), jwt);
        return user;
    }
}
