package com.revature.quizzard.util;

import java.sql.Connection;

//singleton design pattern->will prevent more than one instances to be made
//factory design pattern-> abstracts the messy work of connecting
public class ConnectionFactory {
    //one instance of the factory to be made
    private static ConnectionFactory connectionFactory;//lazy singleton
    private ConnectionFactory(){

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
        return null;
    }
}
