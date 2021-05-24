package com.revature.quizzard.util;

import com.revature.quizzard.daos.UserDAO;
import com.revature.quizzard.screens.*;
import com.revature.quizzard.services.InputValidator;
import com.revature.quizzard.services.UserService;
import com.revature.quizzard.util.datasource.Session;
import com.revature.quizzard.util.logging.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static com.revature.quizzard.Driver.app;

public class AppState {

    private final Logger logger;
    private final ScreenRouter router;
    private boolean appRunning;
    private boolean loggingToConsole;

    public AppState(boolean loggingToConsole) {
        logger = Logger.getLogger(loggingToConsole);
        logger.info("Initializing application");

        this.loggingToConsole = loggingToConsole;
        appRunning = true;

        final Session session = new Session(null);
        final UserDAO userDao = new UserDAO();
        final UserService userService = new UserService(userDao);

        router = new ScreenRouter();
        final BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        final InputValidator inputValidator = new InputValidator(consoleReader, router);

        router.addScreen(new WelcomeScreen(inputValidator, router))
              .addScreen(new RegisterScreen(inputValidator, userService, router))
              .addScreen(new LoginScreen(inputValidator, userService, router, session))
              .addScreen(new DashboardScreen(inputValidator, router, session))
              .addScreen(new UserProfileScreen(inputValidator, router, session));


        logger.info("Application initialized");
    }

    public boolean loggingToConsole() {
        return loggingToConsole;
    }

    public void setLoggingToConsole(boolean loggingToConsole) {
        this.loggingToConsole = loggingToConsole;
    }

    public void startup() {
        router.navigate("/welcome");
        while (appRunning) {
            try {
                router.getCurrentScreen().render();
            } catch (Exception e) {
                logger.fatal(e.getMessage());
                shutdown();
            }
        }
    }

    public void shutdown() {
        logger.info("Shutting down application");
        this.appRunning = false;
    }

}
