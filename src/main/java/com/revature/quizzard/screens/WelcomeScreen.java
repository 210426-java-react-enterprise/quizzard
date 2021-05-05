package com.revature.quizzard.screens;

import com.revature.quizzard.util.ScreenRouter;
import static com.revature.quizzard.Driver.getApp;

import java.io.BufferedReader;

public class WelcomeScreen extends Screen{

    private BufferedReader consoleReader;
    private ScreenRouter router;

    public WelcomeScreen(BufferedReader consoleReader, ScreenRouter router){
        super("WelcomeScreen", "/Welcome");
        this.consoleReader = consoleReader;
        this.router = router;
    }

    @Override
    public void render() {
        System.out.println("welcome to Quizzard!");
        System.out.println("1) Login");
        System.out.println("2) Register");
        System.out.println("3) Exit Application");

        try {
            System.out.print("> ");
            String userSelection = consoleReader.readLine();

            switch (userSelection){
                case "1":
                    System.out.println("navigate to login");
                    router.navigate("/Login");
                    break;
                case "2":
                    System.out.println("navigate to register");
                    router.navigate("/Register");
                    break;
                case "3":
                    System.out.println("Exiting Application!");
                    //Figure out how to tell the app to shut down
                    //System.exit(0); //Very bad practice; Force closes JM, can corrupt files
                    getApp().setAppRunning(false);
                    break;
                default:
                    System.out.println("Invalid Selection");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
