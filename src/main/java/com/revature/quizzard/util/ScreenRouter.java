package com.revature.quizzard.util;

//The goal of the router is to load single instances of Screens,
//As we want to go from one screen to the next, we give it a path
//This will give you the one instance of the screen you want to use
//Rather than creating two or more screen instances which is a memory leak issue

import com.revature.quizzard.screens.Screen;

public class ScreenRouter {
    
    private LinkedList<Screen> screens = new LinkedList<>();
    
    public ScreenRouter addScreen(Screen screen){
        screens.add(screen);
        return this;
    }

    //just to make sure we're on the same page
    // our current get method returns null right?
    public void navigate(String route){
        for (int i = 0; i < screens.size(); i++) {
            Screen screen = screens.get(i);
            if(screens.get(i).getRoute().equals(route)){
                screen.render();
            }
        }
    }
}
