package com.revature.quizzard.util;

//This class will connect us to the database
public class ConnectionFactory {

    //private static reference to itself
    //This will prevent other classes from creating some new connection factory
    //Nobody outside of this class can create a new ConnectionFactory
    private static ConnectionFactory connectionFactory = new ConnectionFactory();

    //private constructor
    private ConnectionFactory(){

    }

    //public getInstance method which will build the connection and return it
    //if already built, then return it
    public static ConnectionFactory getInstance(){
        if(connectionFactory == null){
            connectionFactory = new ConnectionFactory();
        }

        return connectionFactory;
    }

    public static ConnectionFactory getConnection() {
        return null;
    }
}
