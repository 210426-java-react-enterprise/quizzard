package com.revature.quizzard.screens;

import com.revature.quizzard.daos.UserDAO;
import com.revature.quizzard.models.AppUser;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class RegisterScreen {
    //we need this here because we need to persist data that we get into the file/database
    private UserDAO userDAO = new UserDAO();
    private BufferedReader consoleReader;


    public RegisterScreen(BufferedReader consoleReader){
        this.consoleReader = consoleReader;
    }

    public void render() {
        //uninitialied values in the method
        String firstName;
        String lastName;
        String email;
        String username;
        String password;
        int age;


        //Scanners are also a good class to use for this purpose
        //InputStreamReader actually has a pointer from the BufferedReader

        //This uses AutoCloseable
        //You want bufferreader to disconnect from the original resource, and hta resouce being System.in
        //While you could put the BufferedReader outside, you should use try with resources
        //To try to instantiate the AutoCloseable
        try(BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));)
        //Try catch to handle exceptions
        {
            //risky code that might throw an exception


            System.out.println("Register for a new account!");
            System.out.println("+----------------------------+");

            System.out.print("First name: ");
            //get first name from user
            firstName = consoleReader.readLine();

            System.out.print("Last name: ");
            //get last name from user
            lastName = consoleReader.readLine();

            System.out.print("Email: ");
            //get email from user
            email = consoleReader.readLine();

            System.out.print("Username: ");
            //get username
            username = consoleReader.readLine();

            System.out.print("Password: ");
            //get password
            password = consoleReader.readLine();

            System.out.print("Age: ");
            //get age, but you can't use readLine() for numbers
            age = Integer.parseInt(consoleReader.readLine());

            AppUser newUser = new AppUser(firstName, lastName, email, username, password, age);
            System.out.println("New user created: " + newUser);

            userDAO.saveUserToFile(newUser);


        }
        //IOExceptions happen if there's an issue with entering info in the console
        //The pipe operator between two different types of exceptions are useful
        //if you plan to handle two exceptions the same way
        catch(NumberFormatException e){
            //handle the exception here!
            System.err.println("You put in a wrong value for your age! Try again.");
            e.printStackTrace();
            //This makes them redo everything again
            this.render();

        } catch(Exception e){
            System.out.println("General Exception thrown!");
            e.printStackTrace();
        }
        finally{
            //Instead of having nested try-catch you can put a try() around BufferedReader
            //Instead of having to have the nested try-catch around consoleReader.close();
        }
    }

}
