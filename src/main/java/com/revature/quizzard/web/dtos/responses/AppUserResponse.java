package com.revature.quizzard.web.dtos.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.revature.quizzard.models.AppUser;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

import java.util.List;
import java.util.stream.Collectors;

@Jacksonized @Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class AppUserResponse {

    private int id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String role;
    private List<StudySetResponse> userStudySets;

    public AppUserResponse(AppUser user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.role = user.getRole().toString();
        this.userStudySets = user.getUserStudySets().stream().map(StudySetResponse::new).collect(Collectors.toList());
    }

}
