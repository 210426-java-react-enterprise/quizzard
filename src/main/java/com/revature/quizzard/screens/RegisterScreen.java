package com.revature.quizzard.screens;

import com.revature.quizzard.exceptions.InvalidRequestException;
import com.revature.quizzard.exceptions.ResourcePersistenceException;
import com.revature.quizzard.exceptions.UserInputException;
import com.revature.quizzard.models.AppUser;
import com.revature.quizzard.services.InputValidator;
import com.revature.quizzard.services.UserService;
import com.revature.quizzard.util.RegEx;
import com.revature.quizzard.util.ScreenRouter;

import java.io.IOException;

import static com.revature.quizzard.Driver.app;

public class RegisterScreen extends Screen {

    private final UserService userService;

    public RegisterScreen(InputValidator inputValidator, UserService userService, ScreenRouter router) {
        super("RegisterScreen", "/register", inputValidator, router);
        this.userService = userService;
    }

    public void render() {

        try {
            System.out.println("\nRegister for a new account!");
            System.out.println("+-------------------------+");


            String firstName = inputValidator.promptUser("First name: ", "Invalid input.", 3, RegEx.ALPHABETIC_25);
            String lastName = inputValidator.promptUser("Last name: ", "Invalid input.", 3, RegEx.ALPHABETIC_25);
            String email = inputValidator.promptUser("Email: ", "Invalid input.", 3, RegEx.EMAIL);
            String username = inputValidator.promptUser("Username: ", "Invalid input.", 3, RegEx.ALPHANUMERIC_20);
            String password = inputValidator.promptUser("Password: ", "Invalid input.", 3, RegEx.PASSWORD);
            int age = Integer.parseInt(inputValidator.promptUser("Age:  ", "Invalid input.", 3, RegEx.AGE_RANGE));

            AppUser newUser = new AppUser(username, password, email, firstName, lastName, age);
            userService.register(newUser);
            router.navigate("/login");

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
