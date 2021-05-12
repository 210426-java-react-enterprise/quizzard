package com.revature.quizzard.screens;

import com.revature.quizzard.exceptions.AuthenticationException;
import com.revature.quizzard.exceptions.InvalidRequestException;
import com.revature.quizzard.models.AppUser;
import com.revature.quizzard.services.InputValidator;
import com.revature.quizzard.services.UserService;
import com.revature.quizzard.util.RegEx;
import com.revature.quizzard.util.ScreenRouter;

import static com.revature.quizzard.Driver.app;

public class LoginScreen extends Screen {

    private final UserService userService;

    public LoginScreen(InputValidator inputValidator, ScreenRouter router, UserService userService) {
        super("LoginScreen", "/login", inputValidator, router);
        this.userService = userService;
    }

    public void render() throws Exception {

        try {
            System.out.println("\nLog into your account!");
            System.out.println("+---------------------+");

            String username = inputValidator.promptUser("Username: ", "Invalid input.", 3, RegEx.ALPHANUMERIC_20);
            String password = inputValidator.promptUser("Password: ", "Invalid input.", 3, RegEx.PASSWORD);

            AppUser authenticatedUser = userService.authenticate(username, password);
            if (authenticatedUser != null) {
                logger.info("Log in successful!");
                router.navigate("/dashboard");
            } else {
                logger.warn("Log in unsuccessful!");
            }

        } catch (InvalidRequestException | AuthenticationException e) {
            logger.warn(e.getMessage());
        }
    }
}
