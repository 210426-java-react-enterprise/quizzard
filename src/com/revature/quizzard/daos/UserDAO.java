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

    // Searches users.txt in resources folder and returns an AppUser if found
    public AppUser findUserByUsername(String username) throws IOException {

        // Initialize in case username not found in .txt
        AppUser returnUser = null;

        // Try to open users.txt and prepare for search
        try (BufferedReader reader = new BufferedReader(new FileReader("resources/users.txt"))){
            String readLine;
            String[] splitLine;

            // If the reader doesn't return a null keep on looping
            while ((readLine = reader.readLine()) != null) {
                // Split the returned line at every semi-colon
                splitLine = readLine.split(";");

                /*
                    splitLine[0] == stored username
                    if stored username == input username
                    create and return the AppUser
                 */
                if (splitLine[0].equalsIgnoreCase(username)) {
                    returnUser = new AppUser(splitLine[0],
                            splitLine[1],
                            splitLine[2],
                            splitLine[3],
                            splitLine[4],
                            Integer.parseInt(splitLine[5]));
                }

            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Returns AppUser or null for checking purposes.
            return returnUser;
        }
    }
}
