package com.revature.quizzard.util;

import com.revature.quizzard.screens.Screen;
import com.revature.quizzard.util.structures.LinkedList;

public class ScreenRouter {

    private LinkedList<Screen> screens = new LinkedList<>();
    private Screen currentScreen;

    public ScreenRouter addScreen(Screen screen) {
        screens.add(screen);
        return this;
    }

    public void navigate(String route) {
        for (int i = 0; i < screens.size(); i++) {
            Screen screen = screens.get(i);
            if (screen.getRoute().equals(route)) {
                //screen.render();
                this.currentScreen = screen;

            }
        }
    }

    public Screen getCurrentScreen() {
        return currentScreen;
    }

}
