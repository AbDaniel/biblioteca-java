package com.biblioteca.controller;

import com.biblioteca.action.Action;
import com.biblioteca.action.Parser;
import com.biblioteca.enums.LibrarianMenuItem;
import com.biblioteca.listener.ExitLogoutListener;
import com.biblioteca.model.User;
import com.biblioteca.view.LibrarianMenuView;

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
        Action librarianAction = parser.getLibrarianAction(selectedMenuItem);
        librarianAction.addExitLogoutListener(listener);
        librarianAction.execute();
    }

    private boolean isInvalidChoice(LibrarianMenuItem userChoice) {
        return userChoice == null;
    }

    @Override
    public void addExitLogoutListener(ExitLogoutListener listener) {
        this.listener = listener;
    }
}
