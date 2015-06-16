package com.biblioteca.controller;

import com.biblioteca.action.Parser;
import com.biblioteca.enums.LibrarianMenuItem;
import com.biblioteca.listener.ExitLogoutListener;
import com.biblioteca.model.User;
import com.biblioteca.view.LibrarianMenuView;

import static com.biblioteca.constants.Constants.EXIT_CODE;
import static com.biblioteca.constants.Constants.LOGOUT_CODE;

public class LibrarianController implements Controller {

    private LibrarianMenuView menuView;
    private Parser parser;
    private ExitLogoutListener listener;

    public LibrarianController(LibrarianMenuView menuView, Parser parser) {
        this.menuView = menuView;
        this.parser = parser;
    }

    @Override
    public void execute(User user) {
        menuView.displayMenu();
        LibrarianMenuItem selectedMenuItem = menuView.getChoice();

        if (isInvalidChoice(selectedMenuItem))
            return;
        switch (selectedMenuItem) {
            case QUIT:
                listener.update(EXIT_CODE);
                return;
            case LOGOUT:
                listener.update(LOGOUT_CODE);
                return;
        }

        parser.getLibrarianAction(selectedMenuItem).execute();
    }

    private boolean isInvalidChoice(LibrarianMenuItem userChoice) {
        return userChoice == null;
    }

    @Override
    public void addListener(ExitLogoutListener listener) {
        this.listener = listener;
    }
}
