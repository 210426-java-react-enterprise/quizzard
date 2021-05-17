package com.revature.quizzard;

import com.revature.quizzard.util.AppState;

public class Driver {

    private static AppState app = new AppState(true);

    public static void main(String[] args) {
        app.startup();
    }

    public static AppState app() {
        return app;
    }

}
