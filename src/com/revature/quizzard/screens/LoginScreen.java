package com.revature.quizzard.screens;
import java.util.ArrayList;
import com.revature.quizzard.daos.UserDAO;
import com.revature.quizzard.models.AppUser;
import java.io.*;
import java.io.BufferedReader;


public class LoginScreen {
    private UserDAO userDao = new UserDAO(); // ok for now, but actually gross -- fix later
    private BufferedReader consoleReader;

    public LoginScreen(BufferedReader consoleReader) {
        this.consoleReader = consoleReader;
    }
    static String[] userInfo;
    String[] userList;
    public void render() {

        String username;
        String password;


        try {
            // risky code that might through an exception

            System.out.println("Welcome Back! Login Below:");
            System.out.println("+-------------------------+");

            System.out.print("Username: ");
            username = consoleReader.readLine();

            System.out.print("Password: ");
            password = consoleReader.readLine();

            File file = new File("/Users/jbongard/Desktop/Revature/quizzard/quizzard/resources/users.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String data;
            //ArrayList<ArrayList<String>> userArray = new ArrayList<ArrayList<String>>();

            //traverse through the text file and populate our list
            while((data = br.readLine()) != null){
                userInfo = data.split(";");
                userList = userInfo;
                System.out.println(data);
                //TODO: Create a nested array that holds all of our registeredUsers + split line data into parts

            }
            System.out.println(userList);
            //check if user exists in our list
//            if (data != null && data.contains(username) && (data.contains(password))){
//                System.out.println("Login Successful");
//            }else{
//                System.out.println("Login Failed");
//                this.render();
//            }


        } catch (FileNotFoundException fnfe) {
            // do something about these!
            System.err.println("File Not Found Exception Raised");
        } catch (IOException ioe){
            System.err.println("Input Output Exception Raised");
        }
        catch (Exception e) {
            e.printStackTrace(); // include this line while developing/debugging the app!
            // should be logged to a file in a production environment
        }



    }
}
