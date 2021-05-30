package com.revature.quizzard.web.controllers;

import com.revature.quizzard.web.util.Handler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HealthCheckController implements Handler {

    public void health(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setStatus(200);
        resp.setContentType("application/json");
        resp.getWriter().write("{\"status\": \"UP\"}");
    }

}
