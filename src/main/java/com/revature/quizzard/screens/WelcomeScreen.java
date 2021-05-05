package com.revature.quizzard.screens;

import com.revature.quizzard.util.AppState;
import com.revature.quizzard.util.MyBufferedReader;
import com.revature.quizzard.util.ScreenRouter;
//import static com.revature.quizzard.Driver.app;

import java.io.BufferedReader;

public class WelcomeScreen extends Screen {

    private MyBufferedReader consoleReader;
    private ScreenRouter router;
    private static WelcomeScreen instance;
    //private AppState app;

    private WelcomeScreen() {
        super("WelcomeScreen", "/welcome");
        this.consoleReader = MyBufferedReader.getInstance();
        this.router = ScreenRouter.getInstance();
        //this.app = AppState.getInstance();
    }

    public static WelcomeScreen getInstance()
    {
        if (instance == null)
        {
            instance = new WelcomeScreen();
        }
        return instance;
    }

    @Override
    public void render() {

        System.out.println("Welcome to Quizzard!");
        System.out.println("1) Login");
        System.out.println("2) Register");
        System.out.println("3) Exit application");

        try {
            System.out.print("> ");
            String userSelection = consoleReader.readLine();

            switch (userSelection) {
                case "1":
                    System.out.println("Navigating to login screen");
                    router.navigate("/login");
                    break;
                case "2":
                    System.out.println("Navigating to register screen");
                    router.navigate("/register");
                    break;
                case "3":
                    System.out.println("Exiting application!");
                    // we need to figure out how to tell the app the shutdown
//                    System.exit(0); // very bad practice; force closes the JVM
                    AppState.getInstance().setAppRunning(false);
                    break;
                default:
                    System.out.println("Invalid selection!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
