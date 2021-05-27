package com.revature.quizzard.web.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.revature.quizzard.dtos.Credentials;
import com.revature.quizzard.exceptions.AuthenticationException;
import com.revature.quizzard.models.AppUser;
import com.revature.quizzard.services._UserService;
import com.revature.quizzard.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class AuthServlet extends HttpServlet {

    private final Logger logger = Logger.getLogger();

    private final _UserService userService;

    public AuthServlet(_UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);

        if (session != null) {
            session.invalidate();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ObjectMapper mapper = new ObjectMapper();
        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");

        try {
            Credentials creds = mapper.readValue(req.getInputStream(), Credentials.class);
            logger.info("Attempting to authenticate user, %s, with provided credentials", creds.getUsername());

            AppUser authUser = userService.authenticate(creds.getUsername(), creds.getPassword());
            writer.write(mapper.writeValueAsString(authUser));

            req.getSession().setAttribute("this-user", authUser);

        } catch (MismatchedInputException e) {
            logger.warn(e.getMessage());
            resp.setStatus(400);
        } catch (AuthenticationException e) {
            logger.warn(e.getMessage());
            resp.setStatus(401);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            resp.setStatus(500);
        }
    }
}
