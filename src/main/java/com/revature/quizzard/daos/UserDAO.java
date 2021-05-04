package com.revature.quizzard.daos;

import com.revature.quizzard.models.AppUser;

import java.io.*;

public class UserDAO {

    String userDataFile;

    public UserDAO() {
        userDataFile = "resources/users.txt";
    }

    public void saveUserToFile(AppUser newUser) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(userDataFile, true))) {
            writer.write(newUser.toFileString() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public AppUser findUserByUsernameAndPassword(String username, String password) {

        try (BufferedReader reader = new BufferedReader(new FileReader(userDataFile))) {
            String userRecord;
            while ((userRecord = reader.readLine()) != null) {
                String[] userData = userRecord.split(";");
                if (userData[0].equals(username) && userData[1].equals(password)) {
                    return new AppUser(userData[0], userData[1], userData[2], userData[3], userData[4], Integer.parseInt(userData[5]));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

}
