package com.revature.quizzard.util;

import com.revature.quizzard.screens.Screen;

public class ScreenRouter {

	private LinkedList<Screen> screens = new LinkedList<>();

	// Adds individual screens
	public ScreenRouter addScreen(Screen screen) {
		screens.add(screen);
		return this;
	}

	public void navigate(String route) {
		for (int i = 0; i < screens.size(); i++) {
			if (screens.get(i).getRoute().equals(route)); {
				screens.get(i).render();
			}

		}
	}
}
