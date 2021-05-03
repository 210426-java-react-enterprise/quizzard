package com.revature.quizzard;

import com.revature.quizzard.screens.LoginScreen;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Driver {

    public static void main(String[] args) {
//        AppUser newUser = new AppUser("wsingleton", "p4ssw0rd",
//                                      "wezley.singleton@revature.com",
//                                      "Wezley", "Singleton", 30);

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

        try (BufferedReader consoleReader2 = new BufferedReader(new InputStreamReader(System.in))) {
            LoginScreen loginScreen = new LoginScreen(consoleReader2);
            loginScreen.render();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
