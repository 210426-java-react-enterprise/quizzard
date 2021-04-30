package com.revature.quizzard;

import com.revature.quizzard.models.AppUser;
import com.revature.quizzard.screens.RegisterScreen;
import com.revature.quizzard.screens.LoginScreen;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Driver {

    public static void main(String[] args) {

        try (BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))) {
//            RegisterScreen registerScreen = new RegisterScreen(consoleReader);
//            registerScreen.render();
            LoginScreen loginScreen =  new LoginScreen(consoleReader);
            loginScreen.render();
        } catch (Exception e) {
            e.printStackTrace();
        }




    }

}
