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
        try(BufferedReader inRead = new BufferedReader(new FileReader("resources/users.txt"))
        {
            String userData = inRead.readLine();
            //userData.split;


            String[] userInfo = userData.split(";");
            String credentialPassword = userInfo[1];
            String credentialUsername = userInfo[0];





        }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
