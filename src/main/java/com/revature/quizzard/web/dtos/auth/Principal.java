package com.revature.quizzard.web.dtos.auth;

import com.revature.quizzard.models.AppUser;
import io.jsonwebtoken.Claims;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class Principal {

    private int id;
    private String username;
    private AppUser.Role role;

    public Principal(Claims jwtClaims) {
        this.id = Integer.parseInt(jwtClaims.getId());
        this.username = jwtClaims.getSubject();
        this.role = AppUser.Role.valueOf(jwtClaims.get("role", String.class).toUpperCase()); // TODO fix this hacky garbage
    }

    public Principal(AppUser user) {
        this.id = user.getId();;
        this.username = user.getUsername();
        this.role = user.getRole();
    }

}
