package com.revature.quizzard;

import com.revature.quizzard.daos.UserDAO;
import com.revature.quizzard.models.AppUser;
import com.revature.quizzard.screens.MenuScreen;
import com.revature.quizzard.screens.RegisterScreen;
import com.revature.quizzard.screens.LoginScreen;
import com.revature.quizzard.util.JDBCConnector;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Driver {

    public static void main(String[] args) {
        AppUser user = null;
        UserDAO userDAO = new UserDAO();

        try (BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))) {
            MenuScreen menuScreen = new MenuScreen(consoleReader);
            Integer menuChoice = menuScreen.render();
            //this is broken with new screen stuff, will be replaced with new screen stuff later anyway.
//            switch (menuChoice) {
//                case 1:
//                    RegisterScreen registerScreen = new RegisterScreen(consoleReader);
//                    registerScreen.render();
//                    break;
//                case 2:
//                    LoginScreen loginScreen = new LoginScreen(consoleReader);
//                    user = loginScreen.render();
//                    if (user != null) {
//                        AppUser foundUser = userDAO.findUserByUsername(user.getUsername());
//                        if (foundUser == null) {
//                            System.out.println("User not found.");
//                        }
//                        else if (foundUser.getPassword().equals(user.getPassword())) {
//                            System.out.printf("User %s authenticated!\n", user.getUsername());
//                        }
//                        else if(!foundUser.getPassword().equals(user.getPassword())) {
//                            System.out.printf("User %s not authenticated, password mismatch.\n", user.getUsername());
//                        }
//
//                    }
//                    break;
//                //Host: quizzard.c0nbqj7oncuf.us-east-1.rds.amazonaws.com
////        Port: 5432
////        Database name: postgres
////        Schema name: quizzard
////        Username: quizzard_app
////        Password: revature
//                case 3:
//                    JDBCConnector conn = new JDBCConnector(
//                            "quizzard.c0nbqj7oncuf.us-east-1.rds.amazonaws.com",
//                            5432,
//                            "quizzard_app",
//                            "revature",
//                            "quizzard",
//                            "postgres");
//                    break;
//            }
//
//

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
