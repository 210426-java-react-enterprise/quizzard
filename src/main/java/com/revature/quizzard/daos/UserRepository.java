package com.revature.quizzard.daos;

import com.revature.quizzard.exceptions.DataSourceException;
import com.revature.quizzard.models.AppUser;
import com.revature.quizzard.util.logging.Logger;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.Transactional;

import javax.transaction.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    private SessionFactory sessionFactory;
    //private final Logger logger = Logger.getLogger();

    @Autowired
    public UserRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<AppUser> findAllUsers() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from AppUser a where a.id =: a.email", AppUser.class).getResultList();
    }

 /*
    public Optional<AppUser> findUserById(int id) {

        Optional<AppUser> _user = Optional.empty();

        try {
            String sql = "SELECT * FROM quizzard.users WHERE user_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();
            _user = getOne(rs);

        } catch (SQLException e) {
            //logger.warn(e.getMessage());
            throw new DataSourceException();
        }

        return _user;
    }
*/
    public AppUser save(AppUser newUser) {
        Session session = sessionFactory.getCurrentSession();
        session.save(newUser);
        return newUser;


    }

    /*

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
            //logger.warn(e.getMessage());
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
            //logger.warn(e.getMessage());
            throw new DataSourceException();
        }

        return true;
    }

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
            //logger.warn(e.getMessage());
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

     */


}
