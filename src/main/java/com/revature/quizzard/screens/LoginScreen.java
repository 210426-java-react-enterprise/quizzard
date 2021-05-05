package com.revature.quizzard.screens;

import com.revature.quizzard.daos.UserDAO;
import com.revature.quizzard.models.AppUser;
import com.revature.quizzard.util.MyBufferedReader;

import java.io.BufferedReader;

public class LoginScreen extends Screen {

    private final UserDAO userDao;
    private final BufferedReader consoleReader;
    private static LoginScreen instance;

    private LoginScreen() {
        super("LoginScreen", "/login");
        consoleReader = MyBufferedReader.getInstance();
        userDao = UserDAO.getInstance();
    }

    public static LoginScreen getInstance()
    {
        if (instance == null)
        {
            instance = new LoginScreen();
        }
        return instance;
    }

    @Override
    public void render() {

        try {
            String username;
            String password;

            System.out.println("Log into your account!");
            System.out.println("+---------------------+");

            System.out.print("Username: ");
            username = consoleReader.readLine();

            System.out.print("Password: ");
            password = consoleReader.readLine();

            if (username != null && !username.isEmpty() && password != null && !password.isEmpty()) {
                AppUser authenticatedUser = userDao.findUserByUsernameAndPassword(username, password);
                if (authenticatedUser != null) {
                    System.out.println("Login successful!");
                } else {
                    System.out.println("Login failed!");
                }
            } else {
                System.out.println("It looks like you didn't provide any credentials!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
