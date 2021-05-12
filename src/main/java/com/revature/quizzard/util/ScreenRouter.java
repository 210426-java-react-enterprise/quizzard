package com.revature.quizzard.util;

import com.revature.quizzard.exceptions.InvalidRouteException;
import com.revature.quizzard.screens.Screen;
import com.revature.quizzard.util.structures.LinkedList;

import java.util.Collection;

public class ScreenRouter {

    private final LinkedList<Screen> screens = new LinkedList<>();
    private Screen currentScreen;

    public ScreenRouter addScreen(Screen screen) {
        screens.add(screen);
        return this;
    }

    public void navigate(String route) {
        currentScreen = screens.stream()
                               .filter(screen -> screen.getRoute().equals(route))
                               .findFirst()
                               .orElseThrow(InvalidRouteException::new);
    }

    public Screen getCurrentScreen() {
        return currentScreen;
    }

}
