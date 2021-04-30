package com.revature.quizzard.daos;

import com.revature.quizzard.models.AppUser;

import java.io.*;

public class UserDAO {

    //filename: "resources/users.txt"

    public static String[] readNextLineFromFile(BufferedReader reader){
        String line = "";
        String[] strArr;
            try {
                line = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

        if(line == null){
            return strArr = null;
        }
        else {
            strArr = line.split(";");
            return strArr;
        }

    }



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
}
