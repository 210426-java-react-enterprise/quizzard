package com.revature.quizzard.screens;

import com.revature.quizzard.Driver;
import com.revature.quizzard.util.logging.Logger;

import static com.revature.quizzard.Driver.app;

public abstract class Screen {

    protected Logger logger = Logger.getLogger();
    protected String name;
    protected String route;

    public Screen(String name, String route) {
        logger.info("Instantiating %s with route: %s", name, route);
        this.name = name;
        this.route = route;
    }

    public String getName() {
        return name;
    }

    public String getRoute() {
        return route;
    }

    public abstract void render();
}
