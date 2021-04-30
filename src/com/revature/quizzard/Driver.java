package com.revature.quizzard;

import com.revature.quizzard.daos.UserDAO;
import com.revature.quizzard.models.AppUser;
import com.revature.quizzard.screens.LoginScreen;
import com.revature.quizzard.screens.RegisterScreen;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class Driver {

    static UserDAO userDAO = new UserDAO();

    public static void main(String[] args) {
        AppUser newUser = new AppUser("wsingleton", "p4ssw0rd",
                                      "wezley.singleton@revature.com",
                                      "Wezley", "Singleton", 30);

//        newUser.toString()
//        System.out.printf("Hello and welcome, %s! I see that you are %d years old, nice!", newUser.getUsername(), newUser.getAge());

        // doesn't work because %d only works with digits
//        System.out.printf("Test char with digit specifier: %d", 'a');

        // try () {} == try-with-resources
        try (BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))) {
//            RegisterScreen registerScreen = new RegisterScreen(consoleReader, userDAO);
            LoginScreen loginScreen = new LoginScreen(consoleReader, userDAO);
//            registerScreen.render();
            loginScreen.render();
            AppUser user = userDAO.findUserByUsername(loginScreen.getUsername());
            if(user == null)
            {
                System.out.println("Login failed! No such user was found!");
            } else if(loginScreen.getPassword().equals(user.getPassword()))
            {
                System.out.println("Login successful!");
            } else
            {
                System.out.println("Login failed! Wrong password!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }



//        List<AppUser> users = userDAO.loadUserProfile();
//        for (AppUser user : users) {
//            System.out.println(user);
//        }
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
