package com.revature.quizzard.dtos;

public class AppUserDTO {

    private String username;
    private String password;

    public AppUserDTO() {
        super();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
