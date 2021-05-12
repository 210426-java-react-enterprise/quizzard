package com.revature.quizzard.services;

import com.revature.quizzard.exceptions.UserInputException;
import com.revature.quizzard.util.RegEx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class InputValidator {

    private BufferedReader consoleReader;

    public InputValidator(BufferedReader consoleReader) {
        this.consoleReader = consoleReader;
    }

    public String promptUser(String prompt, String error, int maxAttempts, RegEx pattern) throws UserInputException, IOException {
        boolean inputFlag = false;
        int attempts = 1;
        String input;

        do {
            System.out.print(prompt);
            input = consoleReader.readLine();
            if (!(inputFlag = Pattern.compile(pattern.getValue()).matcher(input).find())) {
                System.out.println(error);
                attempts++;
            }
        } while (!inputFlag && attempts <= maxAttempts);

        if (attempts > maxAttempts) {
            throw new UserInputException("Too many failed attempts at input.");
        }
        return input;

    }
}
