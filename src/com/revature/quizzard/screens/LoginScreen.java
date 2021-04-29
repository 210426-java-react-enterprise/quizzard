package com.revature.quizzard.screens;

import com.revature.quizzard.daos.UserDAO;
import com.revature.quizzard.models.AppUser;

import java.io.BufferedReader;

public class LoginScreen {

    private BufferedReader consoleReader;
    private UserDAO userDAO;

    public LoginScreen(BufferedReader consoleReader, UserDAO userDAO) {
        this.consoleReader = consoleReader;
        this.userDAO = userDAO;
    }


    public void render() {

        String username;
        String password;

        try {
            // risky code that might through an exception

            System.out.println("Login to your account");
            System.out.println("+-------------------+");

            System.out.print("username: ");
            username = consoleReader.readLine();

            System.out.print("password: ");
            password = consoleReader.readLine();


        } catch (Exception e) {
            e.printStackTrace(); // include this line while developing/debugging the app!
            // should be logged to a file in a production environment
        }


    }
}
