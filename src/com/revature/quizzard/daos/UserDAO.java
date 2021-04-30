package com.revature.quizzard.daos;

import com.revature.quizzard.models.AppUser;
import com.sun.javafx.collections.MappingChange;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UserDAO {
    public static String UserFilePath = "resources/users.txt";
    private static Map<String,AppUser> Users;

    static{
        Users = fetchUsersFromFile();
    }

    public static void saveUserToFile(AppUser newUser) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(UserFilePath, true))) {
            writer.write(newUser.toFileString() + "\n");
            Users.put(newUser.getUsername(), newUser); //prevents need to reload from disk.  all users are stored in memory
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<String,AppUser> getUsers(){
        return Users;
    }

    public static Map<String,AppUser> fetchUsersFromFile(){

        Map map = new HashMap<String,AppUser>();
        try {
            File myObj = new File(UserFilePath);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] userData = data.split(";", 6);
                AppUser user = new AppUser(userData[0], userData[1], userData[2], userData[3],
                        userData[4], Integer.parseInt(userData[5]) );
                map.put(user.getUsername(), user);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return map;
    }

}
