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
    public AppUser findUserByUsername(String username) {
        return null;
    }

    public boolean readCredentials(String username, String password) {

        String s;
        String[] arrayString = new String[6];

        try (BufferedReader reader = new BufferedReader(new FileReader("resources/users.txt"))) {
            while (reader.ready()) {
                s = reader.readLine();
                arrayString = s.split(";");
                if (arrayString[0].equals(username)) {
                    if (arrayString[1].equals(password)) {
                        return true;
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
}
