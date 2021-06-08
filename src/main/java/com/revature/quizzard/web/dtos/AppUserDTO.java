package com.revature.quizzard.web.dtos;

import com.revature.quizzard.models.AppUser;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AppUserDTO {

    private int id;

    @NotEmpty
    private String username;

    @Email
    private String email;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    private AppUser.Role role;
    private List<StudySetDTO> userStudySets;

    public AppUserDTO() {
        super();
        userStudySets = new ArrayList<>();
    }

    public AppUserDTO(AppUser user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.role = user.getRole();
        this.userStudySets = user.getUserStudySets().stream().map(StudySetDTO::new).collect(Collectors.toList());
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

    public List<StudySetDTO> getFlashcards() {
        return userStudySets;
    }

    public void setFlashcards(List<StudySetDTO> userStudySets) {
        this.userStudySets = userStudySets;
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
