package com.revature.quizzard.users.dtos.responses;

import com.revature.quizzard.study_sets.dtos.responses.StudySetResponse;
import com.revature.quizzard.users.AppUser;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data @NoArgsConstructor
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
