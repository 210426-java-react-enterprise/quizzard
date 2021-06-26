package com.revature.quizzard.auth;

import com.revature.quizzard.auth.dtos.requests.Credentials;
import com.revature.quizzard.auth.dtos.responses.Principal;
import com.revature.quizzard.users.UserService;
import com.revature.quizzard.auth.security.JwtConfig;
import com.revature.quizzard.auth.security.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

import static org.springframework.http.MediaType.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
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
    public Principal authenticate(@RequestBody Credentials credentials, HttpServletResponse resp) {
        Principal user = userService.authenticate(credentials);
        String jwt = tokenGenerator.createJwt(user);
        resp.setHeader(jwtConfig.getHeader(), jwt);
        return user;
    }
}
