package com.revature.quizzard.util;

import com.revature.quizzard.daos.UserDAO;
import com.revature.quizzard.screens.LoginScreen;
import com.revature.quizzard.screens.RegisterScreen;
import com.revature.quizzard.screens.WelcomeScreen;

import java.io.BufferedReader;
import java.io.InputStreamReader;





public class AppState {

    private BufferedReader consoleReader;
    private ScreenRouter router;
    private boolean appRunning;

    public AppState() {
        System.out.println("Initializing application...");

        appRunning = true;
        consoleReader = new BufferedReader(new InputStreamReader(System.in));

        final UserDAO userDao = new UserDAO();

        router = new ScreenRouter();
        router.addScreen(new WelcomeScreen(consoleReader, router))
              .addScreen(new LoginScreen(consoleReader))
              .addScreen(new RegisterScreen(consoleReader));

        System.out.println("Application initialized!");
    }

    public ScreenRouter getRouter() {
        return router;
    }

    public boolean isAppRunning() {
        return appRunning;
    }

    public void setAppRunning(boolean appRunning) {
        this.appRunning = appRunning;
    }


}
