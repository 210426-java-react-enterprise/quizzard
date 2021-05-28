package com.revature.quizzard.web.filters;

import com.revature.quizzard.web.dtos.Principal;
import com.revature.quizzard.web.security.HackyJwtConfig;
import com.revature.quizzard.web.security.JwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebFilter("/*")
public class AuthFilter extends OncePerRequestFilter {

    private Logger logger = LogManager.getLogger();


    @Override
    public void doFilterInternal(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws ServletException, IOException {
        parseToken(req);
        chain.doFilter(req, resp);
    }

    private void parseToken(HttpServletRequest req) {

        try {

            String header = req.getHeader(HackyJwtConfig.getHeader());

            if (header == null || !header.startsWith(HackyJwtConfig.getPrefix())) {
                logger.info("Request originates from an unauthenticated source.");
                return;
            }

            String token = header.replaceAll(HackyJwtConfig.getPrefix(), "");

            Claims jwtClaims = Jwts.parser()
                                   .setSigningKey(HackyJwtConfig.getSigningKey())
                                   .parseClaimsJws(token)
                                   .getBody();

            req.setAttribute("principal", new Principal(jwtClaims));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
