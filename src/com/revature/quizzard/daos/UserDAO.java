package com.revature.quizzard.daos;

import com.revature.quizzard.models.AppUser;

import java.awt.*;
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
        try(BufferedReader reader = new BufferedReader(new FileReader("resources/users.txt"))) {

            while(reader.ready()) {
                String fileLine = reader.readLine();
                String[] parsedLine = fileLine.split(";");

                if (username.equals(parsedLine[0])) {
                    AppUser foundUser = new AppUser(parsedLine[0],
                                                    parsedLine[1],
                                                    parsedLine[2],
                                                    parsedLine[3],
                                                    parsedLine[4],
                                                    Integer.parseInt(parsedLine[5]));
                    return foundUser;
                }

            }

        } catch(Exception e) {

        }
        return null;
    }
}
