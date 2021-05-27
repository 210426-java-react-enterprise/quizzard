package com.revature.quizzard.controllers;

import com.revature.quizzard.dtos.Credentials;
import com.revature.quizzard.exceptions.AuthenticationException;
import com.revature.quizzard.models.AppUser;
import com.revature.quizzard.services.UserService;
import com.revature.quizzard.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.springframework.http.MediaType.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final Logger logger = Logger.getLogger();

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/stop")
    public void delete(HttpServletRequest req){
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }

    @GetMapping(value = "/login", consumes = APPLICATION_JSON_VALUE)
    public void login(@RequestBody Credentials creds, HttpServletRequest req, HttpServletResponse resp) {
        logger.info("Attempting to authenticate user, %s, with provided credentials", creds.getUsername());
        try {
            AppUser authUser = userService.authenticate(creds.getUsername(), creds.getPassword());
            req.getSession().setAttribute("this-user", authUser);
        } catch (AuthenticationException e) {
            logger.warn(e.getMessage());
            resp.setStatus(401);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            resp.setStatus(500);
        }
    }
}


