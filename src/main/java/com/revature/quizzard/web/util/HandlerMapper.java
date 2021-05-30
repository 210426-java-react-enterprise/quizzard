package com.revature.quizzard.web.util;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.Map;

public class HandlerMapper {

    private ServletContext context;
    private Map<HandlerRegistration, HandlerMapping> handlerMap;

    public HandlerMapper(ServletContext context) {
        this.context = context;
        this.handlerMap = new Hashtable<>();
    }

    public HandlerMapper registerHandler(String httpMethod, String resourceName, Handler handlerInstance, Method handlerMethod, String... requestParams) {
        HandlerRegistration handlerRegistration = new HandlerRegistration(httpMethod, "/" + resourceName, requestParams);

        System.out.println("registering handler: " + handlerRegistration);

        handlerMap.put(handlerRegistration, new HandlerMapping(handlerInstance, handlerMethod));
        return this;
    }


    public HandlerMapping getHandler(HttpServletRequest req) {

        String httpMethod = req.getMethod();
        String requestUri = req.getRequestURI();

        if (requestUri.trim().isEmpty()) {
            throw new RuntimeException("Invalid request URI provided!");
        }

        requestUri = requestUri.replace(context.getContextPath(), "");
        HandlerRegistration handlerRegistration = new HandlerRegistration(httpMethod, requestUri, req.getParameterNames());
        System.out.println(handlerRegistration);
        return handlerMap.computeIfAbsent(handlerRegistration, k -> {
            throw new RuntimeException("No handler found with provided uri: " + k + "!");
        });

    }

    @Override
    public String toString() {
        return "HandlerMapper{" +
                "context=" + context +
                ", handlerMap=" + handlerMap +
                '}';
    }
}
