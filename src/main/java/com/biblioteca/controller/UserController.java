package com.biblioteca.controller;

import com.biblioteca.action.Action;
import com.biblioteca.action.Parser;
import com.biblioteca.enums.MenuItem;
import com.biblioteca.listener.ExitLogoutListener;
import com.biblioteca.model.User;
import com.biblioteca.view.MenuView;

import java.util.Map;

import static com.biblioteca.constants.Constants.EXIT_CODE;
import static com.biblioteca.constants.Constants.LOGOUT_CODE;

public class UserController implements Controller {

    private MenuView menuView;
    private Parser parser;
    private ExitLogoutListener listener;

    public UserController(MenuView menuView, Parser parser) {
        this.menuView = menuView;
        this.parser = parser;
    }

    @Override
    public void execute(User user) {
        menuView.displayMenu();
        Map.Entry<MenuItem, String> userChoice = menuView.getUserChoiceAsEntry();
        MenuItem selectedMenuItem = userChoice.getKey();
        switch (selectedMenuItem) {
            case QUIT:
                listener.update(EXIT_CODE);
                return;
            case LOGOUT:
                listener.update(LOGOUT_CODE);
                return;
            case INVALID:
                return;
        }

        Action action = parser.getAction(userChoice, user);
        action.addExitLogoutListener(listener);
        action.execute();
    }

    @Override
    public void addExitLogoutListener(ExitLogoutListener listener) {
        this.listener = listener;
    }

}
