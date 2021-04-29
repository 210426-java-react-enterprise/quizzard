package com.revature.quizzard.daos;
//DAO stands for Data Access Object
//its job is to read, write, update and delete users from a file
//eventually translates to CRUD for a database

import com.revature.quizzard.models.AppUser;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class UserDAO {

    public void saveUserToFile(AppUser newUser){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("resources/users.txt", true))){
            writer.write(newUser.toFileString());

        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
