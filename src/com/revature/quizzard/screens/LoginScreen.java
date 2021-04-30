package com.revature.quizzard.screens;

import com.revature.quizzard.IO.ConsoleReader;
import com.revature.quizzard.daos.UserDAO;
import com.revature.quizzard.models.AppUser;

import java.io.BufferedReader;
import java.util.Map;

public class LoginScreen extends Screen{

    private UserDAO userDao = new UserDAO(); // ok for now, but actually gross -- fix later


    public void render() {
        try {

            String userName = consoleReader.promptIn("Username: ");
            String password = consoleReader.promptIn("Password: ");

            Map<String, AppUser> appUsers = UserDAO.getUsers();

            AppUser user = appUsers.get(userName);

            //validate user exists
            //then validate there's a password
            if (user == null) {
                System.out.println("Login Failed");
            } else if (user.getPassword().equals(password)) {
                System.out.println("Login Success");
            } else {
                System.out.println("Login Failed");
            }

        } catch (Exception e) {
        }
    }


}
