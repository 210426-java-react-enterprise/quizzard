package com.revature.quizzard.screens;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LoginScreen<username> {

    private BufferedReader consoleReader;
    public LoginScreen(BufferedReader consoleReader) {
        this.consoleReader = consoleReader;
    }
    public void render(){
        String username;
        String password;
        try {
            System.out.print("Username: ");
            username = consoleReader.readLine();
            System.out.print("Password: ");
            password = consoleReader.readLine();
            File file = new File(("resources/users.txt"));
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            st = br.readLine();
            String[] credentials = st.split(";");
            // System.out.println(credentials[0] + credentials[1]);
            if (username.equals(credentials[0]) && password.equals(credentials[1])){
                System.out.println("Login Successful!");
            } else {
                System.out.println("Login failed!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
