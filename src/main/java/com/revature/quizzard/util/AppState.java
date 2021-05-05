package com.revature.quizzard.util;

import com.revature.quizzard.daos.UserDAO;
import com.revature.quizzard.screens.LoginScreen;
import com.revature.quizzard.screens.RegisterScreen;
import com.revature.quizzard.screens.WelcomeScreen;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AppState {

    private MyBufferedReader consoleReader = MyBufferedReader.getInstance();
    private ScreenRouter router = ScreenRouter.getInstance();
    private boolean appRunning;
    private static AppState instance;
    private WelcomeScreen welcomeScreen = WelcomeScreen.getInstance();
    private LoginScreen loginScreen = LoginScreen.getInstance();
    private RegisterScreen registerScreen = RegisterScreen.getInstance();

    private AppState() {
        System.out.println("Initializing application...");
        appRunning = true;

        router.addScreen(welcomeScreen);
        router.addScreen(loginScreen);
        router.addScreen(registerScreen);

        System.out.println("Application initialized!");
    }

    public static AppState getInstance()
    {
        if (instance == null)
        {
            instance = new AppState();
        }
        return instance;
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
