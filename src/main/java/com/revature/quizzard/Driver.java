package com.revature.quizzard;

import com.revature.quizzard.util.datasource.ConnectionFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Driver {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName("org.h2.Driver");
        Connection conn = DriverManager.getConnection ("jdbc:h2:~/test", "admin","");
    }
}
