package com.revature.quizzard.screens;

import com.revature.quizzard.daos.UserDAO;
import com.revature.quizzard.models.AppUser;

import java.io.BufferedReader;


public class LoginScreen {
    private UserDAO userDao = new UserDAO(); // ok for now, but actually gross -- fix later
    private BufferedReader consoleReader;

    public LoginScreen(BufferedReader consoleReader) {
        this.consoleReader = consoleReader;
    }

    public void render() {

        try {
            String username;
            String password;
            // risky code that might through an exception

            System.out.println("Welcome Back! Login Below:");
            System.out.println("+-------------------------+");

            System.out.print("Username: ");
            username = consoleReader.readLine();

            System.out.print("Password: ");
            password = consoleReader.readLine();

            if (username != null && !username.isEmpty() && password != null && !password.isEmpty()){
                AppUser authenticatedUser= userDao.loginValidation(username,password);
                if (authenticatedUser != null){
                    System.out.println("Login Succesful!");
                }else{
                    System.out.println("Login failed.");
                }
            }else{
                System.out.println("It looks like you didnt provide valid credentials");
            }
        }


        catch (Exception e) {
            e.printStackTrace(); // include this line while developing/debugging the app!
            // should be logged to a file in a production environment
        }



    }
}
