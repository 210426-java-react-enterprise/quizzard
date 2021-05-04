package com.revature.quizzard.util;

// Singleton design pattern

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

	private static ConnectionFactory connectionFactory;
	private Properties props = new Properties();

	/*static {
		try {
			Class.forName("org.postgreslqul.Driver");
		} catch
	}*/

	private ConnectionFactory() {
		try {
			props.load(new FileReader("src/main/resources/application.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}


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
			conn = DriverManager.getConnection(
					props.getProperty("host-url"),
					props.getProperty("username"),
					props.getProperty("password"));
		} catch (SQLException e ) {
			e.printStackTrace();
		}
		return conn;
	}
}
