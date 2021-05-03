package com.revature.quizzard.screens;

import com.revature.quizzard.models.AppUser;

import java.io.BufferedReader;

public class MenuScreen {

    private BufferedReader consoleReader;
    public MenuScreen(BufferedReader consoleReader) {
        this.consoleReader = consoleReader;
    }

    public Integer render() {

        try {
            System.out.println("Main Meu: ");
            System.out.println("+-------+");

            while(true) {
                String menuChoice;
                Integer intChoice = null;
                System.out.print("1 to register.\n2 to log in.\n");
                menuChoice = consoleReader.readLine();
                try {
                    intChoice = Integer.parseInt(menuChoice);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter 1 or 2.");
                }

                if(intChoice != null) {
                    return intChoice;
                }

            }


        } catch (Exception e) {
            e.printStackTrace(); // include this line while developing/debugging the app!
            // should be logged to a file in a production environment
        }

        return null;
    }
}
