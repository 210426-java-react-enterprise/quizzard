package com.revature.quizzard.models;

public class LoginUser {

    private String username;
    private String password;

    public LoginUser(String username, String password) {

        this.username=username;
        this.password=password;
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

    public String loginString(){
        return String.format("%s;%s",username, password);
    }
}
