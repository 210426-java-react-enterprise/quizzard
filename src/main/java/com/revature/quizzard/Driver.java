package com.revature.quizzard;

import com.revature.quizzard.screens.LoginScreen;
import com.revature.quizzard.util.AppState;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Driver {

    private static AppState app = new AppState();

    public static void main(String[] args) {

        while (app.isAppRunning()){
            app.getScreenRouter().navigate("/Welcome");
        }

    }

    public static AppState getApp() {
        return app;
    }

}
