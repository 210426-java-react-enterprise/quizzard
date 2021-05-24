package com.revature.quizzard.util.datasource;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/*
    Singleton Design Pattern

        - Creational pattern
        - Restricts a class in such a way that only one instance can be made of it.
        - Constructor cannot be invoked outside of this class

        Types:
            - Eager singleton:
                + The class is created "eagerly" or ahead of time by instantiating it
                  on the same line as it is declared.
 */

/*
    Factory Design Pattern

        - Creational pattern
        - Abstracts away the creation logic of some object
 */
public class ConnectionFactory {

    private static ConnectionFactory connectionFactory;
    private Properties props = new Properties();

//    static {
//        try {
//            Class.forName("org.postgresql.Driver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }

    private ConnectionFactory() {
        try {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            InputStream input = loader.getResourceAsStream("application.properties");
            props.load(input);
        } catch (IOException e) {
//            e.printStackTrace();
        }
    }

    public static ConnectionFactory getInstance() {
        if (connectionFactory == null) {
            connectionFactory = new ConnectionFactory();
        }

        return connectionFactory;
    }

    public Connection getConnection() {

        Connection conn = null;

        try {

            conn = DriverManager.getConnection(
                    props.getProperty("host-url"),
                    props.getProperty("username"),
                    props.getProperty("password"));

            conn.setAutoCommit(false);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;

    }

}
