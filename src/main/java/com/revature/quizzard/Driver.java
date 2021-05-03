package com.revature.quizzard;

import com.revature.quizzard.screens.LoginScreen;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Driver {

    public static void main(String[] args) {

//        AppUser newUser = new AppUser("wsingleton", "p4ssw0rd",
//                                      "wezley.singleton@revature.com",
//                                      "Wezley", "Singleton", 30);

        //Welcome to the app, two selection 1) register 2) login
        System.out.print("Welcome to Quizzard! ");
        System.out.print("Please make a selection:");
        System.out.print("1) Register \n 2)Login");
        int selectionUser = 0;
        Scanner selScan = new Scanner(System.in);
        selectionUser = selScan.nextInt();

//        newUser.toString()
//        System.out.printf("Hello and welcome, %s! I see that you are %d years old, nice!", newUser.getUsername(), newUser.getAge());

        // doesn't work because %d only works with digits
//        System.out.printf("Test char with digit specifier: %d", 'a');

        // try () {} == try-with-resources
        try (BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))) {

//            RegisterScreen registerScreen = new RegisterScreen(consoleReader);
//            registerScreen.render();
            LoginScreen loginScreen = new LoginScreen(consoleReader);
            loginScreen.render();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // what we used to have to do prior to Java 7's try-with-resources
//        finally {
//            // this block will execute regardless of whether or not the try block code throws an
//            // exception or executes successfully
//            try {
//                consoleReader.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }


    }

}
