package com.revature.quizzard.screens;

import com.revature.quizzard.daos.UserDAO;
import com.revature.quizzard.models.AppUser;

import java.io.BufferedReader;

<<<<<<< HEAD
 public class LoginScreen {

        private UserDAO userDao = new UserDAO(); // ok for now, but actually gross -- fix later
        private BufferedReader consoleReader;

        public LoginScreen(BufferedReader consoleReader) {
            this.consoleReader = consoleReader;
        }

        public void render() {

            String firstName = null;
            String lastName = null;
            String email = null;
            String username;
            String password;
            int age = 0;

            try {
                // risky code that might through an exception

                System.out.println("Log On!");
                System.out.println("+-------------------------+");

                System.out.print("Username: ");
                username = consoleReader.readLine();

                System.out.print("Password: ");
                password = consoleReader.readLine();

                AppUser userInfo = userDao.readUserFromFile(username, password);
                String str;
                if ((str = userInfo.getUsername()) != null){
                    System.out.println(userInfo.getUsername());
                    System.out.println("Login successful!");
                }else{
                    System.out.print("Login failed!");
                }


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

=======
public class LoginScreen {

    private UserDAO userDao = new UserDAO();
    private BufferedReader consoleReader;

    public LoginScreen(BufferedReader consoleReader) {
        this.consoleReader = consoleReader;
    }
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
>>>>>>> 170f214281d83505b04604c70b1aa32d338daf10
