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

    // TODO implement me!
    public void findUserByUsername(AppUser userName) {
        try(BufferedReader reader = new BufferedReader(new FileReader("resources/users.txt"))){
            String st; st = reader.readLine();
        }catch (IOException e) {
            e.printStackTrace();
        }

        //return null;
    }
}
