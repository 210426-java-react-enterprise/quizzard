package com.revature.quizzard.screens;

import com.revature.quizzard.exceptions.AuthenticationException;
import com.revature.quizzard.exceptions.InvalidRequestException;
import com.revature.quizzard.services.InputValidator;
import com.revature.quizzard.services.UserService;
import com.revature.quizzard.util.RegEx;
import com.revature.quizzard.util.ScreenRouter;
import com.revature.quizzard.util.datasource.Session;

public class LoginScreen extends Screen {

    private final UserService userService;
    private final Session session;

    public LoginScreen(InputValidator inputValidator, UserService userService, ScreenRouter router, Session session) {
        super("LoginScreen", "/login", inputValidator, router);
        this.userService = userService;
        this.session = session;
    }

    public void render() throws Exception {

        try {
            System.out.println("\nLog into your account!");
            System.out.println("+---------------------+");

            String username = inputValidator.promptUser("Username: ", "Invalid input.", 3, RegEx.ALPHANUMERIC_20);
            String password = inputValidator.promptUser("Password: ", "Invalid input.", 3, RegEx.PASSWORD);

            userService.authenticate(username, password);

            if (session.getSessionUser().isPresent()) {
                logger.info("Log in successful!");
                router.navigate("/dashboard");
            } else {
                logger.warn("Log in unsuccessful!");
            }

        } catch (InvalidRequestException e) {
            logger.warn(e.getMessage());
        } catch (AuthenticationException e) {
            String msg = "Unable to login using provided credentials. Please try again.\n";
            System.err.println(msg);
            logger.info(msg);
        }
    }
}
