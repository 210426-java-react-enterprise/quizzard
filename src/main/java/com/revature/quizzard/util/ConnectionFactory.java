package com.revature.quizzard.util;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactory {

    private static ConnectionFactory connectionFactory;

    private ConnectionFactory() {

    }

    public static ConnectionFactory getInstance() {
        if (connectionFactory == null) {
            connectionFactory = new ConnectionFactory();
        }

        return connectionFactory;
    }

    public Connection getConnection() {

        Connection conn = null;

        try{

            conn = DriverManger.getConnection("afsddsa");

        } catch (SQLException e) {

        }

        return conn;
    }

}
