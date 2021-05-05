package com.revature.quizzard.screens;

public abstract class Screen {

    protected String name;
    protected String route;
    private static Screen screen;

    protected Screen(String name, String route) {
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
