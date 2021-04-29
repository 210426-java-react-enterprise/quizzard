package com.revature.quizzard.daos;

import com.revature.quizzard.models.AppUser;

import java.io.*;

public class UserDAO {

    public void saveUserToFile(AppUser newUser) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("resources/users.txt", true))) {
            writer.write(newUser.toFileString() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String[] readUserFromFile() {
        String userInfo ="";
        String[] userData;
        try (BufferedReader reader = new BufferedReader(new FileReader("resources/users.txt"))) {
            userInfo =reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return(userData = userInfo.split(";"));

    }

    // TODO implement me!
    public AppUser findUserByUsername(String username) {
        return null;
    }
}
