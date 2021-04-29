package com.revature.quizzard.models;
/*
Classes must be named the exact same as the file itself

Class names should (for best practice) be in PascalCase
    -Not to be confused with camelCase

    POJO = Plain Ol' Java Object
        -Does not usually contain any methods beyond simple getters and setters
            + maybe the occasional convenience method
 */
public class AppUser {
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private int age;


    //use Alt + Insert to open up the Generate menu to add componenets quickly

    public AppUser(String username, String password, String email, String firstName, String lastName, int age) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getUsername() {
        return username; //you do not have to include this.username because
                         // there is no other username in this scope
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String toFileString(){
        return String.format("%s;%s;%s;%s;%s;%d", username, password, email, firstName, lastName, age);
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }
}
