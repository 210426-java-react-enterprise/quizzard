package com.revature.quizzard.util;

import com.revature.quizzard.screens.Screen;

public class ScreenRouter {

    private LinkedList<Screen> screens;
    private static ScreenRouter instance;

    private ScreenRouter()
    {
        screens = new LinkedList<>();
    }
    public void addScreen(Screen screen) {
        screens.add(screen);
    }

    public static ScreenRouter getInstance()
    {
        if (instance == null)
        {
            instance = new ScreenRouter();
        }
        return instance;
    }

    public void navigate(String route) {
        for (int i = 0; i < screens.size(); i++) {
            Screen screen = screens.get(i);
            if (screen.getRoute().equals(route)) {
                screen.render();
            }
        }
    }

}
