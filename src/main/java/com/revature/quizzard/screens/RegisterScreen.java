package com.revature.quizzard.screens;

import com.revature.quizzard.exceptions.InvalidRequestException;
import com.revature.quizzard.exceptions.ResourcePersistenceException;
import com.revature.quizzard.exceptions.UserInputException;
import com.revature.quizzard.models.AppUser;
import com.revature.quizzard.services.InputValidator;
import com.revature.quizzard.services.UserService;
import com.revature.quizzard.util.ScreenRouter;
import com.revature.quizzard.util.logging.Logger;

import java.io.BufferedReader;
import java.io.IOException;

import static com.revature.quizzard.Driver.app;

public class RegisterScreen extends Screen {

    private final Logger logger = Logger.getLogger();
    private final UserService userService;
    private final BufferedReader consoleReader;

    public RegisterScreen(BufferedReader consoleReader, UserService userService, ScreenRouter router) {
        super("RegisterScreen", "/register", router);
        this.consoleReader = consoleReader;
        this.userService = userService;
    }

    public void render() {

        try {
            System.out.println("Register for a new account!");
            System.out.println("+-------------------------+");


            String firstName = InputValidator.promptUser("First name: ", "Invalid input.", 3, "^[a-zA-Z]{1,25}$", consoleReader);

            String lastName = InputValidator.promptUser("Last name: ", "Invalid input.", 3, "^[a-zA-Z]{1,25}$", consoleReader);

            String email = InputValidator.promptUser("Email: ", "Invalid input.", 3, "^([0-9a-zA-Z.]+@[0-9a-zA-Z]+[.][a-zA-Z]+){1,255}$", consoleReader);

            String username = InputValidator.promptUser("Username: ", "Invalid input.", 3, "^[a-zA-Z0-9]{1,20}$", consoleReader);

            String password = InputValidator.promptUser("Password: ", "Invalid input.", 3, "^(?=.*?[#?!@$%^&*-])[a-zA-Z0-9].{8,255}$", consoleReader);

            int age = Integer.parseInt(InputValidator.promptUser("Age:  ", "Invalid input.", 3, "^[0-9]{1,3}$", consoleReader));

            AppUser newUser = new AppUser(username, password, email, firstName, lastName, age);
            userService.register(newUser);

        } catch (NumberFormatException | InvalidRequestException | ResourcePersistenceException e) {
            logger.warn(e.getMessage());
        } catch(UserInputException e) {
            logger.warn(e.getMessage());
            router.navigate("/welcome");
        } catch (IOException e) {
            logger.fatal(e.getMessage());
            app().shutdown();
        }



    }

}
