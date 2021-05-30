package com.revature.quizzard.web.util;

import java.lang.reflect.Method;

public class HandlerMapping {

    private Handler handlerInstance;
    private Method handleMethod;

    public HandlerMapping(Handler handler, Method handleMethod) {
        this.handlerInstance = handler;
        this.handleMethod = handleMethod;
    }

    public Handler getHandlerInstance() {
        return handlerInstance;
    }

    public Method getHandleMethod() {
        return handleMethod;
    }

}
