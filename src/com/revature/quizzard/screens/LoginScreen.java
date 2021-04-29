package com.revature.quizzard.screens;

import com.revature.quizzard.daos.UserDAO;

import java.io.BufferedReader;

public class LoginScreen {
    private UserDAO userDao = new UserDAO(); // ok for now, but actually gross -- fix later
    private BufferedReader consoleReader;

    public LoginScreen(BufferedReader consoleReader) {
        this.consoleReader = consoleReader;
    }

    public void render() {
        String username;
        String password;

        try {
            System.out.print("Enter Username: ");
            username = consoleReader.readLine(); // throws Exception here

            System.out.print("Password: ");
            password = consoleReader.readLine();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
