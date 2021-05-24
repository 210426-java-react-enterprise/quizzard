package com.revature.quizzard.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.quizzard.controller.UserController;
import com.revature.quizzard.daos.UserDAO;
import com.revature.quizzard.models.AppUser;
import com.revature.quizzard.services.UserService;
import com.revature.quizzard.util.datasource.Session;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Servlet hierarchy
 *
 *          Servlet     ServletConfig   Serializable    (interfaces)
 *                  ^
 *          GenericServlet (abstract class)
 *                  ^
 *          HttpServlet (abstract class)
 *                  ^
 *          Custom Servlet (class)
 */
public class UserServlet extends HttpServlet {

    private Dispatcher dispatcher = new Dispatcher();
    private UserService userService = new UserService(new UserDAO());

    /*
    http verbs
        - actions taken on a resource
            get         READ
            post        CREATE
            put         UPDATE
            delete      DELETE
            patch       UPDATE
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // security logic

        // handling headers

        // filtering content

        // logging user data

        // extract this into dispatcher class

        dispatcher.dataDispatch(req, resp);
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

            /*
            resp.sendRedirect("http://localhost:8080/auth"); // really, this should go to a web page for login
            */

        } else if (!requestingUser.getUsername().equals("wsingleton")) {
            resp.setStatus(403);
            return;
        }

        List<AppUser> users = userService.getAllUsers();
        writer.write(mapper.writeValueAsString(users));

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatcher.dataDispatch(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatcher.dataDispatch(req, resp);
    }
}
