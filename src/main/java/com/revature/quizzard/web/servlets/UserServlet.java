package com.revature.quizzard.web.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.quizzard.daos.UserDAO;
import com.revature.quizzard.exceptions.InvalidRequestException;
import com.revature.quizzard.exceptions.ResourceNotFoundException;
import com.revature.quizzard.models.AppUser;
import com.revature.quizzard.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class UserServlet extends HttpServlet {

    private UserService userService;

    public UserServlet(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");

        HttpSession session = req.getSession(false);
        AppUser requestingUser = (session == null) ? null : (AppUser) session.getAttribute("this-user");

        if (requestingUser == null) {
            resp.setStatus(401);
            return;
        } else if (!requestingUser.getUsername().equals("wsingleton")) {
            resp.setStatus(403);
            return;
        }

        String userIdParam = req.getParameter("id");

        try {
            if (userIdParam == null) {
                List<AppUser> users = userService.getAllUsers();
                writer.write(mapper.writeValueAsString(users));
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

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}