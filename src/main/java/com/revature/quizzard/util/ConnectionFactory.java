package com.revature.quizzard.util;
import java.io.FileNotFoundException;
import java.io.FileReader;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

//singleton design pattern->will prevent more than one instances to be made
//factory design pattern-> abstracts the messy work of connecting
public class ConnectionFactory {
    //one instance of the factory to be made
    private static ConnectionFactory connectionFactory;//lazy singleton
    private Properties props = new Properties();

    static {
        try{
            Class.forName("org.postgresql.Driver");
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    private ConnectionFactory(){
        try {
            props.load(new FileReader("src/main/resources/application.properties"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //lazy because it will only create the instance only
    // when the getInstance method is called
    public static ConnectionFactory getInstance() {
        if (connectionFactory == null) {
            connectionFactory = new ConnectionFactory();
        }
        return connectionFactory;
    }
    public Connection getConnection(){
        Connection conn = null;
        try{

            conn = DriverManager.getConnection(
                    props.getProperty("host-url"),
                    props.getProperty("username"),
                    props.getProperty("password")
            );

        }catch(SQLException sqle){
            sqle.printStackTrace();

        }
        return conn;
    }
}
