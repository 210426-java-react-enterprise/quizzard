package com.revature.quizzard.daos;

import com.revature.quizzard.models.AppUser;
import com.revature.quizzard.util.ConnectionFactory;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    String userDataFile;

    public UserDAO() {
        userDataFile = "src/main/resources/users.txt";
    }

    //TODO implement
    public void saveUserToFile(AppUser newUser) {

    }

    public AppUser findUserByUsernameAndPassword(String username, String password) {
        AppUser user = null;

        try(Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "SELECT * FROM quizzard.users WHERE username = ? and password = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                user = new AppUser();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setEmail(rs.getString("email"));
                user.setAge(rs.getInt("age"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

}
