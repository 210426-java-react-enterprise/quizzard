package com.revature.quizzard.web.servlets;

import com.revature.quizzard.web.util.Handler;
import com.revature.quizzard.web.util.HandlerMapper;
import com.revature.quizzard.web.util.HandlerMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DispatcherServlet extends HttpServlet {

    private HandlerMapper handlerMapper;

    public DispatcherServlet(HandlerMapper handlerMapper) {
        this.handlerMapper = handlerMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatchRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatchRequest(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatchRequest(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatchRequest(req, resp);
    }

    private void dispatchRequest(HttpServletRequest req, HttpServletResponse resp) {
        try {
            HandlerMapping handlerMapping = handlerMapper.getHandler(req);
            Handler handler = handlerMapping.getHandlerInstance();
            handlerMapping.getHandleMethod().invoke(handler, req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(500);
        }
    }

}
