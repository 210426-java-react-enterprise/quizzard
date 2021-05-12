package com.revature.quizzard.screens;

import com.revature.quizzard.Driver;
import com.revature.quizzard.services.InputValidator;
import com.revature.quizzard.util.ScreenRouter;
import com.revature.quizzard.util.logging.Logger;

public abstract class Screen {

    protected Logger logger = Logger.getLogger();
    protected String name;
    protected String route;
    protected ScreenRouter router;
    protected InputValidator inputValidator;

    public Screen(String name, String route, InputValidator inputValidator, ScreenRouter router) {
        logger.info("Instantiating %s with route: %s", name, route);
        this.name = name;
        this.route = route;
        this.inputValidator = inputValidator;
        this.router = router;
    }

    public String getName() {
        return name;
    }

    public String getRoute() {
        return route;
    }

    public abstract void render() throws Exception;

}
