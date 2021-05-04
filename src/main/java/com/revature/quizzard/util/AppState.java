package com.revature.quizzard.util;

import com.revature.quizzard.daos.UserDAO;
import com.revature.quizzard.screens.LoginScreen;
import com.revature.quizzard.screens.RegisterScreen;
import com.revature.quizzard.screens.WelcomeScreen;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.Buffer;

public class AppState {

    private BufferedReader consoleReader;
    private  ScreenRouter router;
    private boolean appRunning;

    public AppState(){
        System.out.println("Initializing application...");

        appRunning = true;
        consoleReader = new BufferedReader((new InputStreamReader(System.in)));

        final UserDAO userDAO = new UserDAO();

        router = new ScreenRouter();
        router.addScreen(new WelcomeScreen(consoleReader, router))
                .addScreen(new LoginScreen(consoleReader))
                .addScreen(new RegisterScreen(consoleReader));

        System.out.println("Application Inistialized!");
    }
}
