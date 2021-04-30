package com.revature.quizzard.screens;

import com.revature.quizzard.daos.UserDAO;
import com.revature.quizzard.models.AppUser;
import java.io.*;
import java.io.BufferedReader;


public class LoginScreen {
    private UserDAO userDao = new UserDAO(); // ok for now, but actually gross -- fix later
    private BufferedReader consoleReader;

    public LoginScreen(BufferedReader consoleReader) {
        this.consoleReader = consoleReader;
    }

    public void render() {

        String username;
        String password;


        try {
            // risky code that might through an exception

            System.out.println("Welcome Back! Login Below:");
            System.out.println("+-------------------------+");

            System.out.print("Username: ");
            username = consoleReader.readLine();

            System.out.print("Password: ");
            password = consoleReader.readLine();

            File file = new File("/Users/jbongard/Desktop/Revature/quizzard/quizzard/users.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String data;
            while((data = br.readLine()) != null){
                //System.out.println(data);
                if (data.contains(username) && (data.contains(password))){
                    System.out.println("Login Successful");
                }else{
                    System.out.println("Login Failed");
                    this.render();
                }
            }


        } catch (FileNotFoundException fnfe) {
            // do something about these!
            System.err.println("File Not Found Exception Raised");
        } catch (IOException ioe){
            System.err.println("Input Output Exception Raised");
        }
        catch (Exception e) {
            e.printStackTrace(); // include this line while developing/debugging the app!
            // should be logged to a file in a production environment
        }



    }
}
