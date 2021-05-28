package com.revature.quizzard.dtos;

import com.revature.quizzard.models.AppUser;

public class AppUserDTO {

    private int id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private AppUser.Role role;

    public AppUserDTO() {
        super();
    }

    public AppUserDTO(AppUser u) {
        id = u.getId();
        username = u.getUsername();
        email = u.getEmail();
        firstName = u.getFirstName();
        lastName = u.getLastName();
        role = u.getRole();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public AppUser.Role getRole() {
        return role;
    }

    public void setRole(AppUser.Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "AppUserDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role=" + role +
                '}';
    }

}
