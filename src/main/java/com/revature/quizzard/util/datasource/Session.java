package com.revature.quizzard.util.datasource;

import com.revature.quizzard.exceptions.DataSourceException;
import com.revature.quizzard.models.AppUser;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class Session {

    private AppUser user;
    private Connection connection;

    public Session(AppUser user) {
        if (user != null) {
            this.user = user;
            this.connection = ConnectionFactory.getInstance().getConnection();
        }
    }

    public Optional<AppUser> getSessionUser() {
        return Optional.of(user);
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new DataSourceException("An error occurred while trying to close the data source connection");
        }
    }

    public Connection getConnection() {
        return connection;
    }

}
