package com.revature.quizzard.screens;

import com.revature.quizzard.exceptions.InvalidRequestException;
import com.revature.quizzard.exceptions.ResourcePersistenceException;
import com.revature.quizzard.models.AppUser;
import com.revature.quizzard.services.UserService;
import com.revature.quizzard.util.logging.Logger;

import java.io.BufferedReader;
import java.io.IOException;

import static com.revature.quizzard.Driver.app;

public class RegisterScreen extends Screen {

    private final Logger logger = Logger.getLogger();
    private final UserService userService;
    private final BufferedReader consoleReader;

    public RegisterScreen(BufferedReader consoleReader, UserService userService) {
        super("RegisterScreen", "/register");
        this.consoleReader = consoleReader;
        this.userService = userService;
    }

    public void render() {

        try {
            System.out.println("Register for a new account!");
            System.out.println("+-------------------------+");

            System.out.print("First name: ");
            String firstName = consoleReader.readLine();

            System.out.print("Last name: ");
            String lastName = consoleReader.readLine();

            System.out.print("Email: ");
            String email = consoleReader.readLine();

            System.out.print("Username: ");
            String username = consoleReader.readLine();

            System.out.print("Password: ");
            String password = consoleReader.readLine();

            System.out.print("Age: ");
            int age = Integer.parseInt(consoleReader.readLine());

            AppUser newUser = new AppUser(username, password, email, firstName, lastName, age);
            userService.register(newUser);

        } catch (NumberFormatException | InvalidRequestException | ResourcePersistenceException e) {
            logger.warn(e.getMessage());
        } catch (IOException e) {
            logger.fatal(e.getMessage());
            app().shutdown();
        }



    }

}
