package com.revature.quizzard;

import com.revature.quizzard.models.AppUser;
import com.revature.quizzard.models.Prompt;
import com.revature.quizzard.screens.LoginScreen;
import com.revature.quizzard.screens.PromptScreen;
import com.revature.quizzard.screens.RegisterScreen;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Driver {

//    private static BufferedReader consoleReader;
    private BufferedReader consoleReader;
    public String prompt(String response) {
        return response;
    }

    public static void main(String[] args) {

//        try (BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))) {
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }

        String response;


        try (BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))) {
//            System.out.print("Would you like to Register? (y/n)");
//            response = consoleReader.readLine();
//
////            System.out.println("response "+ response.getClass().getSimpleName());
//
//            if(response == "y") {
//                System.out.println("in iff");
//
//                RegisterScreen registerScreen = new RegisterScreen(consoleReader);
//                registerScreen.render();
//                LoginScreen loginScreen = new LoginScreen(consoleReader);
//                loginScreen.render();
//            }

            RegisterScreen registerScreen = new RegisterScreen(consoleReader);
            registerScreen.render();
            LoginScreen loginScreen = new LoginScreen(consoleReader);
            loginScreen.render();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
