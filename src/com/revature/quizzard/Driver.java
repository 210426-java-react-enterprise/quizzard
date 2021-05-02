package com.revature.quizzard;

import com.revature.quizzard.models.AppUser;
import com.revature.quizzard.models.Prompt;
import com.revature.quizzard.screens.LoginScreen;
import com.revature.quizzard.screens.PromptScreen;
import com.revature.quizzard.screens.RegisterScreen;
import sun.rmi.runtime.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Driver {

//    private static BufferedReader consoleReader;
    private BufferedReader consoleReader;
    public String prompt(String response) {
        return response;
    }

    public static void main(String[] args) {
//        AppUser newUser = new AppUser("wsingleton", "p4ssw0rd",
//                                      "wezley.singleton@revature.com",
//                                      "Wezley", "Singleton", 30);

//        try (BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))) {
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }

        String response;


        try (BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))) {

//            RegisterScreen registerScreen = new RegisterScreen(consoleReader);
//            registerScreen.render();
            LoginScreen loginScreen = new LoginScreen(consoleReader);
            loginScreen.render();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
