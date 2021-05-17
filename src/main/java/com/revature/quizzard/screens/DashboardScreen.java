package com.revature.quizzard.screens;

import com.revature.quizzard.models.AppUser;
import com.revature.quizzard.services.InputValidator;
import com.revature.quizzard.util.RegEx;
import com.revature.quizzard.util.ScreenRouter;
import com.revature.quizzard.util.datasource.Session;

import javax.naming.AuthenticationException;

public class DashboardScreen extends Screen {

    private Session session;

    public DashboardScreen(InputValidator inputValidator, ScreenRouter router, Session session) {
        super("DashboardScreen", "/dashboard", inputValidator, router);
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

        AppUser sessionUser = session.getSessionUser().get();

        System.out.printf("\n%s's Dashboard\n", sessionUser.getFirstName());
        System.out.println("+---------------------+");

        String menu = "1) View/edit my profile information\n" +
                      "2) View/create study sets\n" +
                      "3) View/create flashcards\n" +
                      "4) Logout\n" +
                      "> ";

        String menuSelection = inputValidator.promptUser(menu, "Invalid input.", 3, RegEx.VALID_DASHBOARD_SCREEN_INPUT);

        switch (menuSelection) {
            case "1":
                router.navigate("/user-profile");
                break;
            case "2":
                router.navigate("/study-sets");
                break;
            case "3":
                router.navigate("/flashcards");
                break;
            case "4":
                session.setUser(null);
                router.navigate("/welcome");
        }
    }
}
