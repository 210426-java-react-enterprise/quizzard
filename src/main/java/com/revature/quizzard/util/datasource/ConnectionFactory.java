package com.revature.quizzard.util.datasource;

import com.revature.quizzard.exceptions.DataSourceException;
import com.revature.quizzard.util.logging.Logger;
import org.h2.jdbcx.JdbcConnectionPool;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

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
            InputStream resourceFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("app.properties");

            if (resourceFile == null) {
                dbUrl = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
                dbUsername = "admin";
                dbPassword = "revature";
                logger.info("Creating embedded database connection pool for database at JDBC url: %s", dbUrl);
                dataSource = JdbcConnectionPool.create(dbUrl, dbUsername, dbPassword);
                EmbeddedDatabaseInitializer.initializeEmbeddedDatabase(dataSource.getConnection(), "import.sql");
            } else {
                Properties props = new Properties();
                props.load(resourceFile);
                dbUrl = props.getProperty("host-url");
                dbUsername = props.getProperty("username");
                dbPassword = props.getProperty("password");
                logger.info("Creating database connection pool for database at JDBC url: %s", dbUrl);
                dataSource = JdbcConnectionPool.create(dbUrl, dbUsername, dbPassword);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void initialize() {
        connectionFactory = new ConnectionFactory();
    }

    public static ConnectionFactory getInstance() {
        if (connectionFactory == null) {
            initialize();
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
