package com.revature.quizzard.web.dtos.requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotEmpty;

@Jacksonized @Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Credentials {
    @NotEmpty private String username;
    @NotEmpty private String password;
}
