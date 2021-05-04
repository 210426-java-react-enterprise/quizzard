package com.revature.quizzard.util;
import java.sql.Connection;
import java.sql.DriverManager;

//Host: quizzard.c0nbqj7oncuf.us-east-1.rds.amazonaws.com
//        Port: 5432
//        Database name: postgres
//        Schema name: quizzard
//        Username: quizzard_app
//        Password: revature
public class JDBCConnector {

    public JDBCConnector(String host, int port, String username, String password, String schema, String dbname) {

        Connection c = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(host, username, password);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }

}
