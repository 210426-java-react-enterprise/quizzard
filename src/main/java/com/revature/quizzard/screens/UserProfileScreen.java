package com.revature.quizzard.screens;

import com.revature.quizzard.services.InputValidator;
import com.revature.quizzard.util.ScreenRouter;
import com.revature.quizzard.util.datasource.Session;

public class UserProfileScreen extends Screen {

    private Session session;

    public UserProfileScreen(InputValidator inputValidator, ScreenRouter router, Session session) {
        super("UserProfileScreen", "/user-profile", inputValidator, router);
        this.session = session;
    }

    @Override
    public void render() throws Exception {

        if (!session.getSessionUser().isPresent()) {
            String msg = "No session user found, navigating to welcome screen.";
            logger.warn(msg);
            System.err.println(msg);
            router.navigate("/welcome");
        }

        router.goBack();

    }

}
