package com.revature.quizzard.screens;

import java.io.BufferedReader;
import java.io.IOException;

public class WelcomeScreen {

    private BufferedReader consoleReader;

    public WelcomeScreen(BufferedReader consoleReader) { this.consoleReader = consoleReader; }
    public void render() {
        String choice;
        try {
            System.out.println("Welcome!");
            System.out.println("+-------------------+");
            System.out.println("Please choose from below: ");
            System.out.println("1) Login");
            System.out.println("2) Register");
            System.out.print("Enter 1 or 2: ");
            choice = consoleReader.readLine();

            if(choice == "1") {
                LoginScreen loginScreen = new LoginScreen(consoleReader);
                loginScreen.render();
            } else if (choice== "2") {
                RegisterScreen registerScreen = new RegisterScreen(consoleReader);
                registerScreen.render();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
