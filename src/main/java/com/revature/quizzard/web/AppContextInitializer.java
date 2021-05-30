package com.revature.quizzard.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.quizzard.daos.UserDAO;
import com.revature.quizzard.services.UserService;
import com.revature.quizzard.util.datasource.ConnectionFactory;
import com.revature.quizzard.util.logging.Logger;
import com.revature.quizzard.web.controllers.AuthController;
import com.revature.quizzard.web.controllers.HealthCheckController;
import com.revature.quizzard.web.controllers.UserController;
import com.revature.quizzard.web.servlets.DispatcherServlet;
import com.revature.quizzard.web.util.HandlerMapper;

import java.lang.reflect.Method;

/**
 *      This class is tied to the startup and shutdown of Tomcat. Just implement
 *      the ServletContextListener and put whatever logic into the overridden
 *      methods. Make sure you inform Tomcat of this class by including it
 *      in your deployment descriptor (web.xml) under the listener tag.
 */
public class AppContextInitializer implements ServletContextListener {

    private Logger logger = Logger.getLogger();

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        logger.info("Servlet context initialized, initializing web application context...");

        ServletContext context = sce.getServletContext();

        try {

            ConnectionFactory.initialize();
            UserDAO userDao = new UserDAO();
            UserService userService = new UserService(userDao);

            HealthCheckController healthCheckController = new HealthCheckController();
            Method get_healthCheck = healthCheckController.getClass().getMethod("health", HttpServletRequest.class, HttpServletResponse.class);

            UserController userController = new UserController(userService);
            Method get_allUsers = userController.getClass().getMethod("getAllUsers", HttpServletRequest.class, HttpServletResponse.class);
            Method get_userById = userController.getClass().getMethod("getUserById", HttpServletRequest.class, HttpServletResponse.class);

            AuthController authController = new AuthController(userService);
            Method post_authenticate = authController.getClass().getMethod("authenticate", HttpServletRequest.class, HttpServletResponse.class);

            HandlerMapper handlerMapping = new HandlerMapper(context);
            handlerMapping.registerHandler("GET", "health", healthCheckController, get_healthCheck);
            handlerMapping.registerHandler("GET", "users", userController, get_allUsers);
            handlerMapping.registerHandler("GET", "users", userController, get_userById, "id");
            handlerMapping.registerHandler("POST", "auth", authController, post_authenticate);

            DispatcherServlet dispatcherServlet = new DispatcherServlet(handlerMapping);

            context.addServlet("DispatcherServlet", dispatcherServlet)
                   .addMapping("/*");

        } catch (Exception e) {
            e.printStackTrace();
        }

        logger.info("Web application context initialized, listening at endpoint: " + context.getContextPath());

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
