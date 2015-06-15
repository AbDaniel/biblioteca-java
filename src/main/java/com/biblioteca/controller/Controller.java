package com.biblioteca.controller;

import com.biblioteca.action.Parser;
import com.biblioteca.constants.Constants;
import com.biblioteca.enums.MenuItem;
import com.biblioteca.listener.ExitLogoutListener;
import com.biblioteca.model.User;
import com.biblioteca.view.MenuView;

import java.util.Map;

public class Controller {

    private MenuView menuView;
    private Parser parser;
    private ExitLogoutListener listener;

    public Controller(MenuView menuView, Parser parser) {
        this.menuView = menuView;
        this.parser = parser;
    }

    public void execute(User user) {
        menuView.displayMenu();
        Map.Entry<MenuItem, String> userChoice = menuView.getUserChoiceAsEntry();

        if (isInvalidChoice(userChoice))
            return;
        MenuItem selectedMenuItem = userChoice.getKey();
        switch (selectedMenuItem) {
            case QUIT:
                listener.update(Constants.EXIT_CODE);
                return;
            case LOGOUT:
                listener.update(Constants.LOGOUT_CODE);
                return;
        }

        parser.getAction(userChoice, user).execute();
    }

    private boolean isInvalidChoice(Map.Entry<MenuItem, String> userChoice) {
        return userChoice == null;
    }

    public void addListener(ExitLogoutListener listener) {
        this.listener = listener;
    }

}
