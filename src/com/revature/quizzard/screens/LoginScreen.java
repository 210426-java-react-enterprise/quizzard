package com.revature.quizzard.screens;

import com.revature.quizzard.daos.UserDAO;
import com.revature.quizzard.models.AppUser;

import java.io.BufferedReader;

public class LoginScreen {

    private UserDAO userDao = new UserDAO(); // ok for now, but actually gross -- fix later
    private BufferedReader consoleReader;

    public LoginScreen(BufferedReader fileReader) {
        this.consoleReader = fileReader;
    }

    public void render() {

        AppUser appUser = null;
        String username;
        String password;
        boolean passwordCheck = false;



        // ok but a little verbose
//        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
//        BufferedReader consoleReader = new BufferedReader(inputStreamReader);

        try {
            // risky code that might throw an exception

            System.out.println("Please sign in.");
            System.out.println("+-------------+");

            // While we don't have a proper AppUser object...
            while (appUser == null) {
                // Prompt user for username
                System.out.print("Username: ");
                username = consoleReader.readLine();

                // Send off the users input to be checked against the contents of resources/users.txt...
                appUser = userDao.findUserByUsername(username);

                // If no matches are found...
                if (appUser == null) {
                    // Notify user and prompt for another username...
                    System.out.printf("%s is an unknown username please try again...", username);
                }
            }

            // After a correct username is entered prompt user for a password
            System.out.print("Password: ");
            password = consoleReader.readLine();

            // Compare user input to our AppUser password (this was created after username check)
            if (appUser.getPassword().equals(password)) {
                // SUCCESS!
                System.out.println("Login successful!");
            } else {
                // FAIL!
                System.out.println("Login failed!");
            }

        } catch (Exception e) {
            e.printStackTrace(); // include this line while developing/debugging the app!
            // should be logged to a file in a production environment
        }



    }

}
