package com.revature.quizzard.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.quizzard.daos.UserDAO;
import com.revature.quizzard.models.AppUser;
import com.revature.quizzard.services.UserService;
import com.revature.quizzard.util.datasource.Session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserController {

    private UserService service = new UserService(new UserDAO(), new Session((null)));


    public void authenticate(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        // json stands for javascript object notation

        // read body of the request
        // parse json
        Map<String, Object> jsonMap = new ObjectMapper().readValue(req.getInputStream(), HashMap.class);

        // call #authenticate with username and password from json
        service.authenticate(jsonMap.get("username").toString(), jsonMap.get("password").toString());

        // return appropriate response
        AppUser user = service.getUser();
        if(user == null){
            resp.getWriter().println("The user never showed up, so here we are");
        } else {
            resp.getWriter().println("here is the user you ordered! \n"+user.toString());
        }
    }

    public void register(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // need to register a user


        // 1. gather information out of the request send by the form
        /*
            post /uri http/1.1
            <headers>
            content-type:

            username=joe
            password=swanson
            email=something@guy.com
            ...
         */
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String fn = req.getParameter("firstname");
        String ln = req.getParameter("lastname");
        int age = Integer.parseInt(req.getParameter("age"));

        // 2. construct an AppUser with that information
        AppUser user = new AppUser(username, password, email, fn, ln, age);

        // 3. save the user in the db with the register method from the service
        service.register(user);


        // 4. optional: create method for sending actual information back to the client
        //status code levels
        // 100 - Information
        // 200 - OK
        // 300 - Redirect
        // 400 - Client side error
        // 500 - Server side error
        resp.setStatus(202);

        // This will get the PrintWriter associated with the Response. This PrintWriter will... write
        //      to the body of the response
        resp.getWriter().print("hello out there! Your user has been created!!!");

        /*
        tomcat >
            webapps >
                quizzard >
                    WEB-INF
                    META-INF
                    war_file
         */

    }
}
