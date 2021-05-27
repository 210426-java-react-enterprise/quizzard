package com.revature.quizzard.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.revature.quizzard.daos.UserDAO;
import com.revature.quizzard.services.UserService;
import com.revature.quizzard.web.servlets.AuthServlet;
import com.revature.quizzard.web.servlets.UserServlet;

/**
 *      This class is tied to the startup and shutdown of Tomcat. Just implement
 *      the ServletContextListener and put whatever logic into the overridden
 *      methods. Make sure you inform Tomcat of this class by including it
 *      in your deployment descriptor (web.xml) under the listener tag.
 */
public class DependencyLoaderListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        UserDAO userDao = new UserDAO();
        UserService userService = new UserService(userDao);

        AuthServlet authServlet = new AuthServlet(userService);
        UserServlet userServlet = new UserServlet(userService);

        ServletContext context = sce.getServletContext();
        context.addServlet("AuthServlet", authServlet).addMapping("/auth");
        context.addServlet("UserServlet", userServlet).addMapping("/users/*");

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}