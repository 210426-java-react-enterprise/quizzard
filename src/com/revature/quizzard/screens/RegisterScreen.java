package com.revature.quizzard.screens;

import com.revature.quizzard.daos.UserDAO;
import com.revature.quizzard.models.AppUser;

import java.io.BufferedReader;

public class RegisterScreen {

    private UserDAO userDao = new UserDAO(); // ok for now, but actually gross -- fix later
    private BufferedReader consoleReader;

    public RegisterScreen(BufferedReader consoleReader) {
        this.consoleReader = consoleReader;
    }

    public void render() {

        String firstName = "";
        String lastName = "";
        String email = "";
        String username = "";
        String password = "";
        int age;
        boolean unique = false; // Form validation
        boolean safeString = false; // Manual sanitization

        // ok but a little verbose
//        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
//        BufferedReader consoleReader = new BufferedReader(inputStreamReader);

        try {
            // risky code that might through an exception

            System.out.println("Register for a new account!");
            System.out.println("+-------------------------+");

            while (safeString == false) {
                System.out.print("First name: ");
                firstName = consoleReader.readLine(); // throws Exception here

                safeString = scCheck(firstName);

                if (safeString == false) {
                    System.out.println("No semi-colons (;) allowed.");
                }
            }

            safeString = false;

            while (safeString == false) {
                System.out.print("Last name: ");
                lastName = consoleReader.readLine();

                safeString = scCheck(lastName);

                if (safeString == false) {
                    System.out.println("No semi-colons (;) allowed.");
                }
            }

            safeString = false;

            while (safeString == false) {
                System.out.print("Email: ");
                email = consoleReader.readLine();

                safeString = scCheck(email);

                if (safeString == false) {
                    System.out.println("No semi-colons (;) allowed.");
                }
            }

            while (!unique) {

                System.out.print("Username: ");
                username = consoleReader.readLine();

                safeString = scCheck(username);

                if (safeString == true) {
                    unique = uniqueCheck(username);

                    if (unique == false) {
                        System.out.printf("%s is already take, try again.\n", username);
                    }
                } else {
                    System.out.println("No semi-colons (;) allowed.");
                }
            }

            safeString = false;

            while (safeString == false) {
                System.out.print("Password: ");
                password = consoleReader.readLine();

                safeString = scCheck(password);

                if (safeString == false) {
                    System.out.println("No semi-colons (;) allowed.");
                }
            }

            System.out.print("Age: ");
            age = Integer.parseInt(consoleReader.readLine());

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

    // Checks a string for a semicolon which would break our save file royally
    private boolean scCheck(String stringToCheck) {
        boolean returnVal = true;

        if (stringToCheck.contains(";")) {
            returnVal = false;
        }

        return returnVal;
    }

    // Makes sure username isn't already taken
    private boolean uniqueCheck(String username) {
        boolean returnVal = true;

        try {
            if (userDao.findUserByUsername(username) != null) {
                returnVal = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return returnVal;
    }

}
