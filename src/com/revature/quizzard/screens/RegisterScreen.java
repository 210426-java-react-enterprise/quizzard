package com.revature.quizzard.screens;

import com.revature.quizzard.daos.UserDAO;
import com.revature.quizzard.models.AppUser;

import java.io.BufferedReader;

public class RegisterScreen extends Screen{

    @Override
    public void render() {
        System.out.println("Register for a new account!");
        System.out.println("+-------------------------+");
        promptAccountDetails();
    }


    public void promptAccountDetails(){
        AppUser newUser = new AppUser();


        try {

            newUser.setFirstName(promptStr("First name: "));
            newUser.setLastName(promptStr("Last name: "));
            newUser.setEmail(promptStr("Email: "));
            newUser.setUsername(promptStr("Username: "));
            newUser.setPassword(promptStr("Password: "));
            newUser.setAge(Integer.parseInt(promptStr("Age: ")));

            UserDAO.saveUserToFile(newUser);

        } catch (NumberFormatException nfe) {
            // do something about these!
            System.err.println("You provided an incorrect value for your age! Please try again!");
            this.render(); // this breaks some stuff! we will need to fix this
        } catch (Exception e) {
            e.printStackTrace(); // include this line while developing/debugging the app!
            // should be logged to a file in a production environment
        }

    }

}
