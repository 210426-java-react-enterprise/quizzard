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

        String menu = "\nWelcome to Quizzard!\n" +
                      "1) Login\n" +
                      "2) Register\n" +
                      "3) Exit application\n" +
                      "> ";

        String userSelection = inputValidator.promptUser(menu, "Invalid selection.", 100, RegEx.VALID_WELCOME_SCREEN_INPUT);

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
