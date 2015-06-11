package com.biblioteca.controller;

import com.biblioteca.action.Dispatcher;
import com.biblioteca.console.MenuView;
import com.biblioteca.enums.MenuItem;
import com.biblioteca.model.Owner;

import static com.biblioteca.enums.MenuItem.LOGOUT;
import static com.biblioteca.enums.MenuItem.QUIT;
import static com.biblioteca.enums.MenuItem.valueOf;

public class Controller {

    private MenuView menuView;
    private Dispatcher dispatcher;

    public Controller(MenuView menuView, Dispatcher dispatcher) {
        this.menuView = menuView;
        this.dispatcher = dispatcher;
    }

    public MenuItem execute(Owner owner) {
        menuView.displayMenu();
        int userChoice = menuView.getUserChoice();

        MenuItem selectedMenuItem = valueOf(userChoice);
        if (selectedMenuItem == QUIT || selectedMenuItem == LOGOUT) {
            return selectedMenuItem;
        }

        dispatcher.dispatch(selectedMenuItem, owner);
        return selectedMenuItem;
    }

}
