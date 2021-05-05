package com.revature.quizzard.screens;

import com.revature.quizzard.daos.UserDAO;
import com.revature.quizzard.models.AppUser;
import com.revature.quizzard.util.MyBufferedReader;

public class RegisterScreen extends Screen {

    private UserDAO userDao;
    private MyBufferedReader consoleReader;
    private static RegisterScreen instance;

    private RegisterScreen() {
        super("RegisterScreen", "/register");
        this.consoleReader = MyBufferedReader.getInstance();
        this.userDao = UserDAO.getInstance();
    }

    public static RegisterScreen getInstance()
    {
        if (instance == null)
        {
            instance = new RegisterScreen();
        }
        return instance;
    }

    public void render() {

        String firstName;
        String lastName;
        String email;
        String username;
        String password;
        int age;

        // ok but a little verbose
//        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
//        BufferedReader consoleReader = new BufferedReader(inputStreamReader);

        try {
            // risky code that might through an exception

            System.out.println("Register for a new account!");
            System.out.println("+-------------------------+");

            System.out.print("First name: ");
            firstName = consoleReader.readLine(); // throws Exception here

            System.out.print("Last name: ");
            lastName = consoleReader.readLine();

            System.out.print("Email: ");
            email = consoleReader.readLine();

            System.out.print("Username: ");
            username = consoleReader.readLine();

            System.out.print("Password: ");
            password = consoleReader.readLine();

            System.out.print("Age: ");
            age = Integer.parseInt(consoleReader.readLine());

            AppUser newUser = new AppUser(username, password, email, firstName, lastName, age);
            userDao.save(newUser);

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
