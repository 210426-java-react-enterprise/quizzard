package com.revature.quizzard.services;

import com.revature.quizzard.exceptions.UserInputException;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.regex.Pattern;

public abstract class InputValidator {

    public static String promptUser(String prompt, String error, int maxAttempts, String pattern, BufferedReader consoleReader) throws UserInputException, IOException {
        boolean inputFlag = false;
        int attempts = 1;
        String input = new String();

        do {
            System.out.printf(prompt);
            input = consoleReader.readLine();
            if (!(inputFlag = Pattern.compile(pattern).matcher(input).find())) {
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
