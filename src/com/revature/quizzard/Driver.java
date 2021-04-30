package com.revature.quizzard;


import com.revature.quizzard.screens.LoginScreen;
import com.revature.quizzard.screens.Screen;
import com.revature.quizzard.screens.WelcomeScreen;


public class Driver {

    public static void main(String[] args) {

        WelcomeScreen welcomeScreen = new WelcomeScreen();
        welcomeScreen.render();
        Screen.closeReader();

    }

}
