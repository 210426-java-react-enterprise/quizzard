package com.revature.quizzard.screens;

import com.revature.quizzard.services.InputValidator;
import com.revature.quizzard.util.RegEx;
import com.revature.quizzard.util.ScreenRouter;
import static com.revature.quizzard.Driver.app;

public class WelcomeScreen extends Screen {

    public WelcomeScreen(InputValidator inputValidator, ScreenRouter router) {
        super("WelcomeScreen", "/welcome", inputValidator, router);
    }

    @Override
    public void render() throws Exception {

        System.out.println("\nWelcome to Quizzard!");
        System.out.println("1) Login");
        System.out.println("2) Register");
        System.out.println("3) Exit application");

        String userSelection = inputValidator.promptUser("> ", "Invalid input.", 100, RegEx.VALID_WELCOME_SCREEN_INPUT);

        switch (userSelection) {
            case "1":
                router.navigate("/login");
                break;
            case "2":
                router.navigate("/register");
                break;
            case "3":
                app().shutdown();
        }

    }
}
