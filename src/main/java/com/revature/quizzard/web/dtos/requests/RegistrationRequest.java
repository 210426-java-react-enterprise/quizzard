package com.revature.quizzard.web.dtos.requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.revature.quizzard.models.AppUser;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Jacksonized @Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegistrationRequest {

    @NotEmpty private String username;
    @NotEmpty private String password;
    @Email private String email;
    @NotEmpty private String firstName;
    @NotEmpty private String lastName;

    public AppUser toUser() {
        return new AppUser(username, password, email, firstName, lastName);
    }

}
