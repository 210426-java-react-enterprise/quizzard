package com.revature.quizzard.screens;

import java.io.BufferedReader;
public class LoginScreen {

    private BufferedReader consoleReader;

    public LoginScreen(BufferedReader consoleReader) {
        this.consoleReader = consoleReader;
    }

    public void render() {

        String username;
        String password;

        try {
            System.out.println("Login to your account!");
            System.out.println("+-------------------------+");

            System.out.print("Username: ");
            username = consoleReader.readLine(); // throws Exception here

            System.out.print("Password: ");
            password = consoleReader.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
