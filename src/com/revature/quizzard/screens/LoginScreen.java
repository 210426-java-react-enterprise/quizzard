package com.revature.quizzard.screens;

import com.revature.quizzard.daos.UserDAO;
import com.revature.quizzard.models.AppUser;

import java.io.BufferedReader;

public class LoginScreen {

    /*
    Implement a login screen that takes in user input for the username and password
     and compares that to data in the users.txt file (inside of resources).
     If a user is found, print out "Login successful!"
     Otherwise, print out "Login failed!"
     */

    private UserDAO userDao = new UserDAO(); // ok for now, but actually gross -- fix later
    private BufferedReader consoleReader;



    public LoginScreen(BufferedReader consoleReader){
        this.consoleReader = consoleReader;
        // System.out.println("loginScreen");
    }

    public void render(){
        String username;
        String password;

        try {


            System.out.println("Login into your account!");
            System.out.println("+-------------------------+");

            System.out.print("Username: ");
            username = consoleReader.readLine();

            System.out.print("Password: ");
            password = consoleReader.readLine();

           AppUser userExist = new AppUser(username,password);
          // userDao.findUserByUsername(userExist);

        }catch(Exception e){
            e.printStackTrace();
        }
    }

  /*  public userExists(String username, String password){

        if (){
            return;
        }
    }*/

}
