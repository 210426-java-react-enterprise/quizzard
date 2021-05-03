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

<<<<<<< HEAD
    public AppUser readUserFromFile(String userName, String password){

        String fileLine;
        AppUser userInfo = new AppUser();

        //AppUser userInfo = new AppUser("","","","","",0);

        try(BufferedReader reader = new BufferedReader(new FileReader("resources/users.txt"))){
            while((fileLine = reader.readLine()) != null ){
                String[] data = fileLine.split(";",6);
                if (data[0].equals(userName) && data[1].equals(password)){
                    userInfo.setUsername(data[0]);
                    userInfo.setPassword(data[1]);
                    userInfo.setEmail(data[2]);
                    userInfo.setFirstName(data[3]);
                    userInfo.setLastName(data[4]);
                    userInfo.setAge(Integer.parseInt(data[5]));
                    System.out.println("username:" + data[0] + " was found!!!");
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return userInfo;
    }

    // TODO implement me!
    public AppUser findUserByUsername(String username) {
=======
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

>>>>>>> 170f214281d83505b04604c70b1aa32d338daf10
        return null;

    }

}
