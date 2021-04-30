package com.revature.quizzard.daos;

import com.revature.quizzard.models.AppUser;
import com.revature.quizzard.models.LoginUser;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class UserDAO {

    public void saveUserToFile(AppUser newUser) {
        System.out.println("new user " + newUser);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("resources/users.txt", true))) {
            writer.write(newUser.toFileString() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void checkForUser(LoginUser login){
        System.out.println("line 22 userdao " + login);

    }

    // TODO implement me!
    public AppUser findUserByUsername(String username) {
        return null;
    }
}
