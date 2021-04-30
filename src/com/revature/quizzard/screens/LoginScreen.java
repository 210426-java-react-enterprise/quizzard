package com.revature.quizzard.screens;

import com.revature.quizzard.models.AppUser;

import java.io.BufferedReader;

public class LoginScreen {

    private BufferedReader consoleReader;

    public void render() {
        String username;
        String password;


        try {
            System.out.println("Login to your account: ");
            System.out.println("+--------------------+");

            System.out.print("Username: ");
            username = consoleReader.readLine();

            System.out.print("Password: ");
            password = consoleReader.readLine();

            
        } catch (Exception e) {
            e.printStackTrace(); // include this line while developing/debugging the app!
            // should be logged to a file in a production environment
        }



    }
}
