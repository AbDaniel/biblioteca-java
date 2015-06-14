package com.biblioteca.controller;

import com.biblioteca.action.Parser;
import com.biblioteca.enums.MenuItem;
import com.biblioteca.model.User;
import com.biblioteca.view.MenuView;

import java.util.Map;

import static com.biblioteca.enums.MenuItem.LOGOUT;
import static com.biblioteca.enums.MenuItem.QUIT;

public class Controller {

    private MenuView menuView;
    private Parser parser;

    public Controller(MenuView menuView, Parser parser) {
        this.menuView = menuView;
        this.parser = parser;
    }

    public MenuItem execute(User user) {
        menuView.displayMenu();
        Map.Entry<MenuItem, String> userChoice = menuView.getUserChoiceAsEntry();

        if (isInvalidChoice(userChoice))
            return null;
        MenuItem selectedMenuItem = userChoice.getKey();
        if (isExitOrLogout(selectedMenuItem)) {
            return selectedMenuItem;
        }

        parser.getAction(userChoice, user).execute();
        return selectedMenuItem;
    }

    private boolean isInvalidChoice(Map.Entry<MenuItem, String> userChoice) {
        return userChoice == null;
    }

    private boolean isExitOrLogout(MenuItem selectedMenuItem) {
        return selectedMenuItem == QUIT || selectedMenuItem == LOGOUT;
    }

}
