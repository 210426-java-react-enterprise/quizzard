package com.revature.quizzard.web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.revature.quizzard.dtos.Credentials;
import com.revature.quizzard.exceptions.AuthenticationException;
import com.revature.quizzard.models.AppUser;
import com.revature.quizzard.services.UserService;
import com.revature.quizzard.util.logging.Logger;
import com.revature.quizzard.web.util.Handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AuthController implements Handler {

    private Logger logger = Logger.getLogger();
    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    public void authenticate(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");

        try {
            Credentials creds = mapper.readValue(req.getInputStream(), Credentials.class);
            logger.info("Attempting to authenticate user, %s, with provided credentials", creds.getUsername());

            AppUser authUser = userService.authenticate(creds.getUsername(), creds.getPassword());
            writer.write(mapper.writeValueAsString(authUser));

            req.getSession().setAttribute("this-user", authUser);

        } catch (MismatchedInputException e) {
            logger.warn(e.getMessage());
            resp.setStatus(400);
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
