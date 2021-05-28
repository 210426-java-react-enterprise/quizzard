package com.revature.quizzard.web.dtos;

import com.revature.quizzard.models.AppUser;
import io.jsonwebtoken.Claims;

public class Principal {

    private int id;
    private String username;
    private AppUser.Role role;

    public Principal(Claims jwtClaims) {
        this.id = Integer.parseInt(jwtClaims.getId());
        this.username = jwtClaims.getSubject();
        this.role = AppUser.Role.valueOf(jwtClaims.get("role", String.class));
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

    public AppUser.Role getRole() {
        return role;
    }

    public void setRole(AppUser.Role role) {
        this.role = role;
    }

}
