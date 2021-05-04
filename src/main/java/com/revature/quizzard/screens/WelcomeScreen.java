package com.revature.quizzard.screens;

import com.revature.quizzard.util.ScreenRouter;

import java.io.BufferedReader;

//static import of the static getter in Driver
//this allows you to use it as if it was the Driver.java class
import static com.revature.quizzard.Driver.app;

public class WelcomeScreen extends Screen {

    private BufferedReader consoleReader;
    //This is dependency injection, our WelcomeScreen depends on
    //Screen router to function
    //Here we inject in our ScreenRouter
    private ScreenRouter router;

    public WelcomeScreen(BufferedReader consoleReader, ScreenRouter router){
        super("WelcomeScreen", "/welcome");
        //Passing along the Buffered Reader instead of instantiating new ones
        this.consoleReader = consoleReader;
        this.router = router;
    }

    @Override
    public void render(){
        System.out.println("Welcome to Quizzard!");
        System.out.println("1) Login");
        System.out.println("2) Register");
        System.out.println("3) Exit application");

        try{
            System.out.println("> ");
            String userSelection = consoleReader.readLine();

            switch(userSelection){
                case "1":
                    System.out.println("Navigating to login screen.");
                    //invoke the ScreenRouter to navigate to login screen
                    router.navigate("/login");
                    break;
                case "2":
                    //do something
                    System.out.println("Navigating to register screen.");
                    router.navigate("/register");
                    break;
                case "3":
                    System.out.println("Exiting application...");
                    //we need to figure out how to tell the app to shutdown

                    /*
                    One way you can do it is using
                    System.exit(0) //very bad practice, force closes the JVM
                    This can corrupt files if you end an application poorly
                     */

                    //This allows access to your AppState class which helps control exiting
                    //uses the static app() in Driver class and sets appRunning to false
                    //turns off app
                    app().setAppRunning(false);


                    break;
                default:
                    System.out.println("Invalid selection!");
            }
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
