package com.revature.quizzard.util;

import com.revature.quizzard.daos.UserDAO;
import com.revature.quizzard.screens.LoginScreen;
import com.revature.quizzard.screens.RegisterScreen;
import com.revature.quizzard.screens.WelcomeScreen;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AppState {

    private BufferedReader consoleReader;
    private ScreenRouter screenRouter;
    private boolean appRunning;

    public AppState(){
        System.out.println("Initializing Application....");

        this.appRunning = true;
        this.consoleReader = new BufferedReader(new InputStreamReader(System.in));

        final UserDAO userDAO = new UserDAO();

        screenRouter = new ScreenRouter();
        screenRouter.addScreen(new WelcomeScreen(consoleReader, screenRouter))
                .addScreen(new LoginScreen(consoleReader))
                .addScreen(new RegisterScreen(consoleReader));

        System.out.println("Application Initialized!");
    }

    public boolean isAppRunning() {
        return appRunning;
    }

    public void setAppRunning(boolean appRunning) {
        this.appRunning = appRunning;
    }

    public ScreenRouter getScreenRouter() {
        return screenRouter;
    }
}
