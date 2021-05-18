package com.revature.quizzard.daos;

import com.revature.quizzard.models.AppUser;
import com.revature.quizzard.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
   public void save(AppUser newUser){
       try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
           String sqlInsertUser = "insert into quizzard.users (username , password , email , first_name , last_name , age ) values (?,?,?,?,?,?)";
           PreparedStatement pstmt = conn.prepareStatement(sqlInsertUser,new String[]{"user_id"});
           pstmt.setString(1,newUser.getUsername());
           pstmt.setString(2,newUser.getPassword());
           pstmt.setString(3,newUser.getEmail());
           pstmt.setString(4,newUser.getFirstName());
           pstmt.setString(5,newUser.getLastName());
           pstmt.setInt(6,newUser.getAge());
           int rowsInserted = pstmt.executeUpdate();
           //grabs the generated key from the new user saved
           if(rowsInserted != 0){
               ResultSet rs = pstmt.getGeneratedKeys();
               while(rs.next()){
                   newUser.setId(rs.getInt("user_id"));
               }
           }
       } catch (SQLException throwables) {
           throwables.printStackTrace();
       }
   }

    public AppUser loginValidation(String username,String password){
        AppUser user = null;
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "select * from quizzard.users where username = ? and password = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,username);
            pstmt.setString(2,password);

            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                user = new AppUser();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setAge(rs.getInt("age"));

            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // TODO implement me: You can only delete an account when signed in
    public void deleteAccount(AppUser user){
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "delete * from quizzard.users where user_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,user.getId());

            ResultSet rs = pstmt.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    public Boolean isEmailAvailible(String email){
       try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "select * from quizzard.users where email = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,email);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()){
                return false;
            }
        } catch (SQLException throwables) {
           throwables.printStackTrace();
        }
       return true;
    }
    public Boolean isUsernameAvailible(String username){
       try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "select * from quizzard.users where username = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,username);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()){
                return false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }
}
