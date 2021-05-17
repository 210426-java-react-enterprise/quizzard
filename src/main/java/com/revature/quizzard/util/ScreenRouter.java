package com.revature.quizzard.util;

import com.revature.quizzard.exceptions.InvalidRouteException;
import com.revature.quizzard.screens.Screen;
import com.revature.quizzard.util.logging.Logger;

import java.util.LinkedList;
//import com.revature.structures.LinkedList;

public class ScreenRouter {

    private Logger logger = Logger.getLogger();
    private LinkedList<Screen> screenHistory = new LinkedList<>();
    private LinkedList<Screen> screens = new LinkedList<>();
    private Screen currentScreen;

    public ScreenRouter addScreen(Screen screen) {
        logger.info("Loading %s into router with route: %s", screen.getName(), screen.getRoute());
        screens.add(screen);
        return this;
    }

    public void navigate(String route) {
        logger.info("Navigating to %s", route);
        currentScreen = screens.stream()
                               .filter(screen -> screen.getRoute().equals(route))
                               .findFirst()
                               .orElseThrow(() -> new InvalidRouteException(route));
      
        screenHistory.add(currentScreen);

    }

    public Screen getCurrentScreen() {
        return currentScreen;
    }

    public void goBack() {
        Screen previousScreen = screenHistory.get(screenHistory.size() - 2);
        logger.info("Going back to previous screen: %s", previousScreen.getRoute());
        currentScreen = previousScreen;
        screenHistory.add(currentScreen);
    }

}
