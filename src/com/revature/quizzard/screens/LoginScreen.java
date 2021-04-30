package com.revature.quizzard.screens;

import com.revature.quizzard.models.AppUser;

import java.io.BufferedReader;
import com.revature.quizzard.models.AppUser;

public class LoginScreen {

    private BufferedReader consoleReader;
    public LoginScreen(BufferedReader consoleReader) {
        this.consoleReader = consoleReader;
    }

    public AppUser render() {
        String username;
        String password;


        try {
            System.out.println("Login to your account: ");
            System.out.println("+--------------------+");

            System.out.print("Username: ");
            username = consoleReader.readLine();

            System.out.print("Password: ");
            password = consoleReader.readLine();

            AppUser loginUser = new AppUser(username, password);
            return loginUser;

            
        } catch (Exception e) {
            e.printStackTrace(); // include this line while developing/debugging the app!
            // should be logged to a file in a production environment
        }

        return null;
    }
}
