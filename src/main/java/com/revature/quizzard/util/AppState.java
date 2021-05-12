package com.revature.quizzard.util;

import com.revature.quizzard.daos.UserDAO;
import com.revature.quizzard.screens.DashboardScreen;
import com.revature.quizzard.screens.LoginScreen;
import com.revature.quizzard.screens.RegisterScreen;
import com.revature.quizzard.screens.WelcomeScreen;
import com.revature.quizzard.services.InputValidator;
import com.revature.quizzard.services.UserService;
import com.revature.quizzard.util.logging.Logger;

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

        final InputValidator inputValidator = new InputValidator();
        final UserDAO userDao = new UserDAO();
        final UserService userService = new UserService(userDao);

        router = new ScreenRouter();
        router.addScreen(new WelcomeScreen(inputValidator, router))
              .addScreen(new LoginScreen(inputValidator, router, userService))
              .addScreen(new RegisterScreen(inputValidator, userService, router))
              .addScreen(new DashboardScreen(inputValidator, router));

        logger.info("Application initialized");
    }

    public boolean loggingToConsole() {
        return loggingToConsole;
    }

    public void setLoggingToConsole(boolean loggingToConsole) {
        this.loggingToConsole = loggingToConsole;
    }

    public void startup() {
        logger.info("Navigating to welcome screen");
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
