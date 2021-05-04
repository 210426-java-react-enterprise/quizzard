package com.revature.quizzard;

import com.revature.quizzard.screens.LoginScreen;
import com.revature.quizzard.screens.RegisterScreen;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Driver {

    public static void main(String[] args) {
//        AppUser newUser = new AppUser("wsingleton", "p4ssw0rd",
//                                      "wezley.singleton@revature.com",
//                                      "Wezley", "Singleton", 30);

        int userInput ;


//        newUser.toString()
//        System.out.printf("Hello and welcome, %s! I see that you are %d years old, nice!", newUser.getUsername(), newUser.getAge());

        // doesn't work because %d only works with digits
//        System.out.printf("Test char with digit specifier: %d", 'a');
        System.out.print("Press 1 for Registration and 2 to login: ");
        Scanner scan = new Scanner(System.in);
        userInput = scan.nextInt();

        switch(userInput) {
            case 1:
                try (BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))) {
                    RegisterScreen registerScreen = new RegisterScreen(consoleReader);
                    registerScreen.render();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                /*try (BufferedReader consoleReader = new BufferedReader(new FileReader("resources/user.txt"))) {
                    LoginScreen loginScreen = new LoginScreen(consoleReader);
                    loginScreen.render();
                }catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                System.out.println("try again");
                LoginScreen loginScreen = new LoginScreen(consoleReader);
                loginScreen.render();
        }*/
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

}
