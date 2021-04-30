package com.revature.quizzard.screens;

import com.revature.quizzard.daos.UserDAO;
import com.revature.quizzard.models.AppUser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class LoginScreen {

    public static void render(String filename) {

        Scanner scan = new Scanner(System.in);

        System.out.println("Login Screen");
        System.out.println("+==================");

        System.out.print("Enter your username: \n");
        String username = scan.nextLine();

        System.out.print("Enter your password:  \n");
        String password = scan.nextLine();

        boolean tester = false;
        String[] parsed;

        try(BufferedReader reader = new BufferedReader(new FileReader(filename))){
            String message = "";
            while(!tester){
                parsed = UserDAO.readNextLineFromFile(reader);

                if(parsed == null){
                    message = "Login failed! Username/Password may be incorrect.";
                    tester = true;
                }
                else if(parsed[0].equals(username) && parsed[1].equals(password)){
                    message = "Login successful!";
                    tester = true;
                }
                else if(parsed == null){
                    message = "Login failed! Username/Password may be incorrect.";
                    tester = true;
                }
                else{
                    message = "Login failed!";
                }

            }

            System.out.println(message);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
