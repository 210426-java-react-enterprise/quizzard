package com.revature.quizzard.daos;

import com.revature.quizzard.models.AppUser;
import com.revature.quizzard.models.LoginUser;

import java.io.*;

public class UserDAO {

    public void saveUserToFile(AppUser newUser) {
        System.out.println("new user " + newUser);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("resources/users.txt", true))) {
            writer.write(newUser.toFileString() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void checkForUser(LoginUser login) {
        String[] arrOfLogin = login.loginString().split(";", 5);

        try {
            String line;
           int appearances = 0;
            for (int i = 0; i < arrOfLogin.length; i++) {
                BufferedReader br = new BufferedReader(new FileReader("resources/users.txt"));
                while ((line = br.readLine()) != null) {
                    System.out.println("line " + br.readLine());
                    if (line.contains(arrOfLogin[i])) {
                        appearances++;
                        break;
                    }
                }
            }

            if (appearances == arrOfLogin.length){
                System.out.println("Login Successful!");
            } else if (appearances < arrOfLogin.length) {
                System.out.println("Login Failed.");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // TODO implement me!
    public AppUser findUserByUsername(String username) {
        return null;
    }
}
