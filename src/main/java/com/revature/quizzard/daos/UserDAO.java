package com.revature.quizzard.daos;

import com.revature.quizzard.models.AppUser;
import com.revature.quizzard.util.ConnectionFactory;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    private static UserDAO instance;

    private UserDAO()
    {

    }

    public static UserDAO getInstance()
    {
        if (instance == null)
        {
            instance = new UserDAO();
        }
        return instance;
    }

    // TODO (Associate task) Implement me!
    public void save(AppUser newUser) {

        try(Connection connection = ConnectionFactory.getInstance().getConnection())
        {
            String sql =    "insert into quizzard.users (username, password, first_name, last_name, email, age) " +
                                                "values (?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, newUser.getUsername());
            preparedStatement.setString(2, newUser.getPassword());
            preparedStatement.setString(3, newUser.getFirstName());
            preparedStatement.setString(4, newUser.getLastName());
            preparedStatement.setString(5, newUser.getEmail());
            preparedStatement.setInt(6, newUser.getAge());

            int serverUpdate = preparedStatement.executeUpdate();
            if (serverUpdate == 0) throw new SQLException("UserDAO.save(AppUser), preparedStatement.executeUpdate() returned 0 rows affected!");
            System.out.println("Rows effected: " + serverUpdate);

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public AppUser findUserByUsernameAndPassword(String username, String password) {

        AppUser user = null;

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "select * from quizzard.users where username = ? and password = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
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
