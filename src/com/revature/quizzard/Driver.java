package com.revature.quizzard;


import com.revature.quizzard.daos.UserDAO;
import com.revature.quizzard.models.AppUser;
import com.revature.quizzard.screens.LoginScreen;
import com.revature.quizzard.utilities.MyList;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Driver {

    static UserDAO userDAO = new UserDAO();

    public static void main(String[] args) {

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
//        MyList<Integer> myList = new MyList<>();
//        myList.add(10);
//        myList.add(20);
//        myList.add(30);
//        myList.add(40);
//        myList.add(50);
//
//        for(int i : myList)
//        {
//            System.out.println(i);
//        }

    }

}
