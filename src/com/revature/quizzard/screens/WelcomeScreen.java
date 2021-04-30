package com.revature.quizzard.screens;

import java.io.IOException;

public class WelcomeScreen extends Screen{
    @Override
    public void render() {
        System.out.printf("Welcome to %s\n", Screen.AppName);
        try {
            char selectedScreen = 'n';
            Screen nextScreen;
            String userIn = promptStr(
                    "Press 1 to login\n" +
                            "Press 2 to register\n");


            if(userIn.length() > 0)
                selectedScreen = userIn.charAt(0);

            switch (selectedScreen) {
                case '1':
                    nextScreen = new LoginScreen();
                    break;
                case '2':
                    nextScreen = new RegisterScreen();
                    break;
                default:
                    nextScreen = new WelcomeScreen();
            }

            nextScreen.render();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
