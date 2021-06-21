package com.revature.quizzard.web.controllers;

import com.revature.quizzard.models.AppUser;
import com.revature.quizzard.web.dtos.auth.Principal;
import com.revature.quizzard.web.security.TokenGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class UserControllerIntegrationTestSuite {

    private MockMvc mockMvc;
    private WebApplicationContext webContext;
    private TokenGenerator tokenGenerator;

    @Autowired
    public UserControllerIntegrationTestSuite(WebApplicationContext webContext, TokenGenerator tokenGenerator) {
        this.webContext = webContext;
        this.tokenGenerator = tokenGenerator;
    }

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webContext).build();
    }

    @Test
    public void test_getUserById_givenValidId() throws Exception {

        Principal mockAdminUser = new Principal();
        mockAdminUser.setId(42);
        mockAdminUser.setUsername("mock-admin");
        mockAdminUser.setRole(AppUser.Role.ADMIN);
        String token = tokenGenerator.createJwt(mockAdminUser);

        this.mockMvc.perform(get("/users?id={id}", 1).header("Authorization", token))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                    .andExpect(jsonPath("$[0].id").value(1));

    }



}
