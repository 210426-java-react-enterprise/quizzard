package com.revature.quizzard;


import com.revature.quizzard.screens.LoginScreen;
import com.revature.quizzard.screens.Screen;


public class Driver {

    public static void main(String[] args) {

        LoginScreen loginScreen = new LoginScreen();
        loginScreen.render();
        Screen.closeReader();

    }

}
