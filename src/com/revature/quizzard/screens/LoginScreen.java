package com.revature.quizzard.screens;

import com.revature.quizzard.daos.UserDAO;
import com.revature.quizzard.models.AppUser;

import java.io.BufferedReader;

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

                AppUser user = userDao.readUserFromFile(username, password);
                String str;
                if ((str = user.getUsername()) != null){
                    System.out.println(user.getUsername());
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

