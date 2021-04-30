package com.revature.quizzard;

import com.revature.quizzard.models.AppUser;
import com.revature.quizzard.screens.LoginScreen;
import com.revature.quizzard.screens.RegisterScreen;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Driver {

    public static void main(String[] args) {

        int userInput = 0;

        AppUser newUser = new AppUser("wsingleton", "p4ssw0rd",
                                      "wezley.singleton@revature.com",
                                      "Wezley", "Singleton", 30);

//        newUser.toString()
//        System.out.printf("Hello and welcome, %s! I see that you are %d years old, nice!", newUser.getUsername(), newUser.getAge());

        // doesn't work because %d only works with digits
//        System.out.printf("Test char with digit specifier: %d", 'a');

        // try () {} == try-with-resources
        try (BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))) {

            while (userInput != 1 || userInput != 2) {
                // Prompt user for desired functionality
                System.out.println("Welcome! Would you like to sign in or register a new account?");
                System.out.println("1: Sign In.");
                System.out.println("2: Register.");

                // Assign the users input to userInput
                userInput = Integer.parseInt(consoleReader.readLine());
                System.out.println(userInput);

                // Once functionality is set show the correct screen via switch
                switch (userInput) {
                    case 1:
                        // Instantiate login object
                        LoginScreen loginScreen = new LoginScreen(consoleReader);
                        // Present login screen
                        loginScreen.render();
                        // No break exit program instead
                        System.exit(0);
                    case 2:
                        // Instantiate register object
                        RegisterScreen registerScreen = new RegisterScreen(consoleReader);
                        // Present registration screen
                        registerScreen.render();
                        break;
                    default:
                        System.out.println("Invalid entry!");
                        break;
                }
            }
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
