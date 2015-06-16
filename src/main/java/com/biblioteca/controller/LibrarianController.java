package com.biblioteca.controller;

import com.biblioteca.action.Parser;
import com.biblioteca.enums.LibrarianMenuItem;
import com.biblioteca.listener.ExitLogoutListener;
import com.biblioteca.model.User;
import com.biblioteca.view.LibrarianMenuView;

import static com.biblioteca.constants.Constants.EXIT_CODE;
import static com.biblioteca.constants.Constants.LOGOUT_CODE;

public class LibrarianController {

    private LibrarianMenuView menuView;
    private Parser parser;
    private ExitLogoutListener listener;

    public LibrarianController(LibrarianMenuView menuView, Parser parser) {
        this.menuView = menuView;
        this.parser = parser;
    }

    public void execute(User user) {
        menuView.displayMenu();
    }

    private boolean isInvalidChoice(LibrarianMenuItem userChoice) {
        return userChoice == null;
    }

    public void addListener(ExitLogoutListener listener) {
        this.listener = listener;
    }
}
