package com.revature.quizzard.users.dtos.requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.revature.quizzard.users.AppUser;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data @NoArgsConstructor
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
