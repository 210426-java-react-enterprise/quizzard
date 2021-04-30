package com.revature.quizzard.screens;

import com.revature.quizzard.daos.UserDAO;
import com.revature.quizzard.models.AppUser;

import java.io.BufferedReader;
import java.util.Map;

public class LoginScreen {

    private UserDAO userDao = new UserDAO(); // ok for now, but actually gross -- fix later
    private BufferedReader consoleReader;

    public LoginScreen(BufferedReader consoleReader) {
        this.consoleReader = consoleReader;
    }

    public void render() {
        try {
            System.out.print("UserName: ");
            String userName = consoleReader.readLine(); // throws Exception here

            System.out.print("Password: ");
            String password = consoleReader.readLine();

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
