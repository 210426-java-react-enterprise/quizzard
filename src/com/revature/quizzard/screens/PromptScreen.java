package com.revature.quizzard.screens;

import com.revature.quizzard.Driver;
import com.revature.quizzard.models.Prompt;

import java.io.BufferedReader;

public class PromptScreen {

    private BufferedReader consoleReader;

    public PromptScreen(BufferedReader consoleReader) {
        this.consoleReader = consoleReader;
    }

    public void render() {
        String response;

        try {
            System.out.print("Would you like to Register? y/n");
            response = consoleReader.readLine();

           Prompt newPrompt = new Prompt(response);
           newPrompt.setResponse(response);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
