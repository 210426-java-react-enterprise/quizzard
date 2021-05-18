package com.revature.quizzard.util.datasource;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
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

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private ConnectionFactory() {
        /*
        try {
            props.load(new FileReader("webapp/WEB-INF/application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

         */
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

            conn = DriverManager.getConnection("jdbc:postgresql://rev-postgresql.csduntui8sfv.us-east-2.rds.amazonaws.com/postgres?schemaName=quizzard",
                                                   "ocastillo",
                                                    "P4ssw0rd$$$");

                    /*
                    props.getProperty("host-url"),
                    props.getProperty("username"),
                    props.getProperty("password"));
                    */


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;

    }

}
