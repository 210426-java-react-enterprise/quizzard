package com.revature.quizzard.daos;

import com.revature.quizzard.exceptions.DataSourceException;
import com.revature.quizzard.models.AppUser;
import com.revature.quizzard.util.logging.Logger;

import org.springframework.transaction.annotation.Transactional;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAO {

    private final Logger logger = Logger.getLogger();

    @Transactional
    public List<AppUser> findAllUsers(Connection conn) {

        List<AppUser> users = new ArrayList<>();

        try {

            String sql = "SELECT * FROM quizzard.users";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                AppUser temp = new AppUser();
                temp.setId(rs.getInt("user_id"));
                temp.setUsername(rs.getString("username"));
                temp.setPassword(rs.getString("password"));
                temp.setFirstName(rs.getString("first_name"));
                temp.setLastName(rs.getString("last_name"));
                temp.setEmail(rs.getString("email"));
                temp.setAge(rs.getInt("age"));
                users.add(temp);
            }


        } catch (SQLException e) {
            logger.warn(e.getMessage());
            throw new DataSourceException();
        }

        return users;
    }

    @Transactional
    public Optional<AppUser> findUserById(Connection conn, int id) {

        Optional<AppUser> _user = Optional.empty();

        try {
            String sql = "SELECT * FROM quizzard.users WHERE user_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();
            _user = getOne(rs);

        } catch (SQLException e) {
            logger.warn(e.getMessage());
            throw new DataSourceException();
        }

        return _user;
    }

    @Transactional
    public void save(Connection conn, AppUser newUser) {

        try {

            String sqlInsertUser = "insert into quizzard.users (username , password , email , first_name , last_name , age ) values (?,?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sqlInsertUser, new String[] { "user_id" });
            pstmt.setString(1,newUser.getUsername());
            pstmt.setString(2,newUser.getPassword());
            pstmt.setString(3,newUser.getEmail());
            pstmt.setString(4,newUser.getFirstName());
            pstmt.setString(5,newUser.getLastName());
            pstmt.setInt(6,newUser.getAge());
            int rowsInserted = pstmt.executeUpdate();

            if (rowsInserted != 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                while (rs.next()) {
                    newUser.setId(rs.getInt("user_id"));
                }
            }

        } catch (SQLException e) {
            logger.warn(e.getMessage());
            throw new DataSourceException();
        }

    }

    @Transactional
    public boolean isUsernameAvailable(Connection conn, String username) {
        try {

            String sql = "select * from quizzard.users where username = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return false;
            }

        } catch (SQLException e) {
            logger.warn(e.getMessage());
            throw new DataSourceException();
        }

        return true;

    }

    @Transactional
    public boolean isEmailAvailable(Connection conn, String email) {
        try {

            String sql = "select * from quizzard.users where email = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return false;
            }

        } catch (SQLException e) {
            logger.warn(e.getMessage());
            throw new DataSourceException();
        }

        return true;
    }

    @Transactional
    public Optional<AppUser> findUserByUsernameAndPassword(Connection conn, String username, String password) {

        Optional<AppUser> _user = Optional.empty();

        try {

            String sql = "select * from quizzard.users where username = ? and password = ?";
            if(conn == null) {
                throw new NullPointerException(System.getProperty("host_url") +
                        " is what has been given as the host url from environment variables \n and the username is: "+
                        System.getProperty("db_username") + " with a password of: "+System.getProperty("db_password"));
            }
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();
            _user = getOne(rs);

        } catch (SQLException e) {
            logger.warn(e.getMessage());
            throw new DataSourceException();
        }

        return _user;

    }

    @Transactional
    private Optional<AppUser> getOne(ResultSet rs) throws SQLException {

        AppUser user = null;

        if (rs.next()) {
            user = new AppUser();
            user.setId(rs.getInt("user_id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setEmail(rs.getString("email"));
            user.setAge(rs.getInt("age"));
        }

        return Optional.ofNullable(user);
    
    }


}
