package com.revature.quizzard.servlets;

import com.revature.quizzard.daos.UserDAO;
import com.revature.quizzard.services.UserService;
import com.revature.quizzard.util.datasource.Session;

import javax.servlet.http.HttpServlet;

public class UserServlet extends HttpServlet {

    private UserService service = new UserService(new UserDAO(), new Session(null));



}
