package com.revature.quizzard.screens;

import com.revature.quizzard.daos.UserDAO;
import com.revature.quizzard.models.AppUser;

import java.io.BufferedReader;

public class LoginScreen {

    private BufferedReader consoleReader;
    private UserDAO userDAO;
    String username;
    String password;

    public LoginScreen(BufferedReader consoleReader, UserDAO userDAO) {
        this.consoleReader = consoleReader;
        this.userDAO = userDAO;
    }


    public void render() {

        try {
            // risky code that might through an exception
            username = null;
            password = null;

            System.out.println("\nLogin to your account");
            System.out.println("+-------------------+\n");

            System.out.print("username: ");
            username = consoleReader.readLine();

            System.out.print("password: ");
            password = consoleReader.readLine();

        } catch (Exception e) {
            e.printStackTrace(); // include this line while developing/debugging the app!
            // should be logged to a file in a production environment
        }

    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
