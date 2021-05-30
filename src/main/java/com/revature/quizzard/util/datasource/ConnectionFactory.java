package com.revature.quizzard.util.datasource;

import com.revature.quizzard.exceptions.DataSourceException;
import com.revature.quizzard.util.logging.Logger;
import org.h2.jdbcx.JdbcConnectionPool;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
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

    private Logger logger = Logger.getLogger();
    private DataSource dataSource;
    private String dbUrl;
    private String dbUsername;
    private String dbPassword;

    static {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private ConnectionFactory() {

        try {
            Properties props = new Properties();
            props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("app.properties"));
            dbUrl = props.getProperty("host-url");
            dbUsername = props.getProperty("username");
            dbPassword = props.getProperty("password");
            logger.info("Creating embedded H2 database at: %s", dbUrl);
            dataSource = JdbcConnectionPool.create(dbUrl, dbUsername, dbPassword);
        } catch (Exception e) {
            e.printStackTrace();
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
            conn = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (conn == null) {
            throw new DataSourceException("Could not establish a connection to the database!");
        }

        return conn;

    }

}
