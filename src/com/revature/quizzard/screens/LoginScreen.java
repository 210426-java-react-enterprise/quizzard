package com.revature.quizzard.screens;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LoginScreen {

    private static BufferedReader consoleReader;

    public LoginScreen(BufferedReader consoleReader) {
        this.consoleReader = consoleReader;
    }

    public static void render() {

        //only fields to compare credentials


        try (BufferedReader inRead = new BufferedReader(new FileReader("resources/users.txt"))) {
             String userData = inRead.readLine();


             String[] userInfo = userData.split(";");
             String cPassword = userInfo[1];
             String cUsername = userInfo[0];

             System.out.print("Provide Username: ");
             String username = consoleReader.readLine();

             if (username.equals(cUsername)) {
                System.out.print("Provide Password: ");
                String password = consoleReader.readLine();
                if (password.equals(cPassword)) {
                    System.out.println("Login Successful");
                } else {
                    System.out.println("Login failed!");
                }
            } else {
                 System.out.println("Login failed!");
            }
//
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
