package com.revature.quizzard.screens;

import com.revature.quizzard.services.InputValidator;
import com.revature.quizzard.util.ScreenRouter;

public class DashboardScreen extends Screen {

    public DashboardScreen(InputValidator inputValidator, ScreenRouter router) {
        super("DashboardScreen", "/dashboard", inputValidator, router);
    }

    @Override
    public void render() {
        System.out.println(name + " under construction");
    }
}
