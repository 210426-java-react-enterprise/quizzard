package com.revature.quizzard.screens;

import com.revature.quizzard.daos.UserDAO;
import com.revature.quizzard.models.AppUser;

import java.io.BufferedReader;

public class RegisterScreen extends Screen{

    private UserDAO userDao = new UserDAO(); // ok for now, but actually gross -- fix later


    public void render() {

        String firstName;
        String lastName;
        String email;
        String username;
        String password;
        int age;

        // ok but a little verbose
//        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
//        BufferedReader consoleReader = new BufferedReader(inputStreamReader);

        try {
            // risky code that might through an exception

            System.out.println("Register for a new account!");
            System.out.println("+-------------------------+");

            firstName = promptIn("First name: ");
            lastName = promptIn("Last name: ");
            email = promptIn("Email: ");
            username = promptIn("Username: ");
            password = promptIn("Password: ");
            age =  Integer.parseInt(promptIn("Age: "));


            AppUser newUser = new AppUser(username, password, email, firstName, lastName, age);
            userDao.saveUserToFile(newUser);

        } catch (NumberFormatException nfe) {
            // do something about these!
            System.err.println("You provided an incorrect value for your age! Please try again!");
            this.render(); // this breaks some stuff! we will need to fix this
        } catch (Exception e) {
            e.printStackTrace(); // include this line while developing/debugging the app!
            // should be logged to a file in a production environment
        }



    }

}
