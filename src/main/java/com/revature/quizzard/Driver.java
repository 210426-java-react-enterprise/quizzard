package com.revature.quizzard;

import com.revature.quizzard.util.AppState;

public class Driver {

    private static AppState app = AppState.getInstance();

    public static void main(String[] args) {
        while (app.isAppRunning()) {
            app.getRouter().navigate("/welcome");
        }
    }
}
