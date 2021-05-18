package com.revature.quizzard;

import com.revature.quizzard.util.AppState;

public class Driver {

    private static AppState app = new AppState(true);


    public static void main(String[] args) {
<<<<<<< HEAD
        app.startup();
=======
        while (app.isAppRunning()) {
            app.getRouter().navigate("/welcome");

        }
>>>>>>> 2608f070aac5d4396983e75bae07100bc5e438a4
    }


    public static AppState app() {
        return app;

    }

}
