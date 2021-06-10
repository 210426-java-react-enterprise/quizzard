package com.revature.quizzard.web.security;

import com.revature.quizzard.models.AppUser;
import com.revature.quizzard.web.dtos.Principal;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenGenerator {

    private JwtConfig jwtConfig;

    @Autowired
    public TokenGenerator(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    public String createJwt(Principal subject) {

        long now = System.currentTimeMillis();

        JwtBuilder builder = Jwts.builder()
                                 .setId(Integer.toString(subject.getId()))
                                 .setSubject(subject.getUsername())
                                 .claim("role", subject.getRole().toString())
                                 .setIssuer("revature")
                                 .setIssuedAt(new Date(now))
                                 .setExpiration(new Date(now + jwtConfig.getExpiration()))
                                 .signWith(jwtConfig.getSigAlg(), jwtConfig.getSigningKey());

        return jwtConfig.getPrefix() + " " + builder.compact();

    }

    public JwtConfig getJwtConfig() {
        return jwtConfig;
    }
}
