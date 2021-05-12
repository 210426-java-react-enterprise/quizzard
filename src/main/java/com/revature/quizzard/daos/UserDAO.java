package com.revature.quizzard.daos;

import com.revature.quizzard.exceptions.DataSourceException;
import com.revature.quizzard.models.AppUser;
import com.revature.quizzard.util.logging.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserDAO {

    private final Logger logger = Logger.getLogger();

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

    public Optional<AppUser> findUserByUsernameAndPassword(Connection conn, String username, String password) {

        Optional<AppUser> _user = Optional.empty();

        try {

            String sql = "select * from quizzard.users where username = ? and password = ?";
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
