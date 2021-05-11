package com.revature.quizzard.screens;

import com.revature.quizzard.Driver;
import com.revature.quizzard.daos.UserDAO;
import com.revature.quizzard.exceptions.AuthenticationException;
import com.revature.quizzard.exceptions.InvalidRequestException;
import com.revature.quizzard.models.AppUser;
import com.revature.quizzard.services.UserService;
import com.revature.quizzard.util.ScreenRouter;
import com.revature.quizzard.util.logging.Logger;

import java.io.BufferedReader;

import static com.revature.quizzard.Driver.app;

public class LoginScreen extends Screen {

    private Logger logger = Logger.getLogger();
    private BufferedReader consoleReader;
    private ScreenRouter router;
    private UserService userService;

    public LoginScreen(BufferedReader consoleReader, ScreenRouter router, UserService userService) {
        super("LoginScreen", "/login", router);
        this.consoleReader = consoleReader;
        this.router = router;
        this.userService = userService;
    }

    public void render() {

        try {
            String username;
            String password;

            System.out.println("Log into your account!");
            System.out.println("+---------------------+");

            System.out.print("Username: ");
            username = consoleReader.readLine();

            System.out.print("Password: ");
            password = consoleReader.readLine();

            AppUser authenticatedUser = userService.authenticate(username, password);
            if (authenticatedUser != null) {
                router.navigate("/dashboard");
            }

        } catch (InvalidRequestException | AuthenticationException e) {
            logger.warn(e.getMessage());
        } catch (Exception e) {
            logger.fatal(e.getMessage());
            app().shutdown();
        }
    }
}
