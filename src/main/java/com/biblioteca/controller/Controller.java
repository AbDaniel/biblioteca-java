package com.biblioteca.controller;

import com.biblioteca.action.Parser;
import com.biblioteca.model.User;
import com.biblioteca.view.MenuView;
import com.biblioteca.enums.MenuItem;

import java.util.Map;

import static com.biblioteca.enums.MenuItem.LOGOUT;
import static com.biblioteca.enums.MenuItem.QUIT;
import static com.biblioteca.enums.MenuItem.valueOf;

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

        if (userChoice == null)
            return null;
        MenuItem selectedMenuItem = userChoice.getKey();
        if (selectedMenuItem == QUIT || selectedMenuItem == LOGOUT || selectedMenuItem == null) {
            return selectedMenuItem;
        }

        parser.getAction(selectedMenuItem, user).execute();
        return selectedMenuItem;
    }

}
