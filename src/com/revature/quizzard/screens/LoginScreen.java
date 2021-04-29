package com.revature.quizzard.screens;

import java.io.*;

public class LoginScreen {

    private BufferedReader consoleReader;

    public LoginScreen(BufferedReader consoleReader) {
        this.consoleReader = consoleReader;
    }

    public void render() {

        //only fields to compare credentials
         String username;
         String password;

        try (BufferedReader inRead = new BufferedReader(new FileReader("resources/users.txt")) {
             String userData = inRead.readLine();
            //userData.split;


             String[] userInfo = userData.split(";");
             String credentialPassword = userInfo[1];
             String credentialUsername = userInfo[0];

             System.out.print("Provide Username");
             String providedUsername = consoleReader.readLine();

             if (providedUsername.equals(credentialUsername)){

            }

        } catch(FileNotFoundException e) {
        e.printStackTrace();
          } catch(IOException e) {
            e.printStackTrace();
    }

}

