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
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
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
        // redirect, will send client to other servers
        resp.sendRedirect("https://www.google.com");
        // forward, will send client to other resources without them knowing.
//        req.getRequestDispatcher("/someOtherResource").forward(req, resp);
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
