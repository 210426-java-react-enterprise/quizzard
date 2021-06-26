package com.revature.quizzard.auth.dtos.requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data @NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Credentials {
    @NotEmpty private String username;
    @NotEmpty private String password;
}
