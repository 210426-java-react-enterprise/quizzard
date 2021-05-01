package com.revature.quizzard.daos;

import com.revature.quizzard.models.AppUser;
import com.revature.quizzard.utilities.MyList;

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
    public MyList<AppUser> loadUserProfile()
    {
        String[] result = new String[10];
        MyList<AppUser> userList = new MyList<>();

        try(BufferedReader reader = new BufferedReader(new FileReader("resources/users.txt")))
        {
            int index = 0;
            while ((result[index] = reader.readLine()) != null)
            {
                String[] resultArray = result[index].split(";");
                userList.add(new AppUser(resultArray[0], resultArray[1], resultArray[2], resultArray[3], resultArray[4], Integer.parseInt(resultArray[5])));

                index++;
            }
        } catch (FileNotFoundException e)
        {
            System.err.println("File not found!");

        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return userList;
    }

    public AppUser findUserByUsername(String username) {

        MyList<AppUser> users = loadUserProfile();

        for (AppUser user : users) {
            if(user != null && user.getUsername().equals(username))
                return user;
        }
        return null;
    }
}
