package com.revature.quizzard.web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.quizzard.exceptions.InvalidRequestException;
import com.revature.quizzard.exceptions.ResourceNotFoundException;
import com.revature.quizzard.models.AppUser;
import com.revature.quizzard.services.UserService;
import com.revature.quizzard.web.util.Handler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class UserController implements Handler {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public void getAllUsers(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");

//        HttpSession session = req.getSession(false);
//        AppUser requestingUser = (session == null) ? null : (AppUser) session.getAttribute("this-user");

//        if (requestingUser == null) {
//            resp.setStatus(401);
//            return;
//        } else if (!requestingUser.getUsername().equals("wsingleton")) {
//            resp.setStatus(403);
//            return;
//        }

        try {
            List<AppUser> users = userService.getAllUsers();
            writer.write(mapper.writeValueAsString(users));
        } catch (ResourceNotFoundException e) {
            resp.setStatus(404);
        } catch (InvalidRequestException e) {
            resp.setStatus(400);
        }
    }

    public void getUserById(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");

        HttpSession session = req.getSession(false);
        AppUser requestingUser = (session == null) ? null : (AppUser) session.getAttribute("this-user");

        String userIdParam = req.getParameter("id");

        try {
            if (userIdParam == null) {
                throw new InvalidRequestException("Invalid user id provided.");
            } else {
                AppUser user = userService.getUserById(userIdParam);
                writer.write(mapper.writeValueAsString(user));
            }
        } catch (ResourceNotFoundException e) {
            resp.setStatus(404);
        } catch (InvalidRequestException e) {
            resp.setStatus(400);
        }
    }
}
