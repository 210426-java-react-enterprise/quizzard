package com.revature.quizzard.util;

// Singleton design pattern

import java.sql.Connection;
import java.sql.DriverManager;
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

	// no longer singleton design pattern
	// the following is specific to this class
	public Connection getConnection() {

		Connection conn = null;

		try {
			conn = DriverManager.getConnection("host-url", "username", "password");
		} catch (SQLException e ) {

		}
		return conn;
	}
}
