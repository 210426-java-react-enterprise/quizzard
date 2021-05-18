package com.revature.quizzard.servlet;

import com.revature.quizzard.controller.UserController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Dispatcher {
    private UserController controller = new UserController();

    public void dataDispatch(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        switch (req.getRequestURI()) {
            case "quizzard/user.data":
                controller.register(req, resp);
                break;
            case "quizzard/auth.data":
                // here we are going to authenticate a user taken from a json in the request body
                controller.authenticate(req, resp);
                break;
            default:
                // some default handler
                resp.setStatus(400);
                resp.getWriter().println("The request uri was not recognized");

        }
    }
}
