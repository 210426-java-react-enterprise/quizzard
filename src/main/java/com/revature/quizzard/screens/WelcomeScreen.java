package com.revature.quizzard.screens;

import com.revature.quizzard.util.ScreenRouter;

import java.io.BufferedReader;

public class WelcomeScreen extends Screen{
    private BufferedReader consoleReader;
    private ScreenRouter router;

    public WelcomeScreen(BufferedReader consoleReader, ScreenRouter router) {

    }

    @Override
    public void render() {
        System.out.println("Welcome to Quizzard!");
        System.out.print("2. Login.\n2. Register.\n3. Exit.\n");

        try {
            System.out.print(">");
            String userSelection = consoleReader.readLine();

            switch(userSelection) {
                case "1":
                    System.out.println("Navigating to ");
                    break;
                case "2":
                    break;
                case "3":
                    break;
                default:
                    System.out.println("Invalid Selection");
            }
        }
    }
}
