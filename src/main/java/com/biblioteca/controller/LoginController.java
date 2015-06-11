package com.biblioteca.controller;

import com.biblioteca.action.Login;
import com.biblioteca.console.View;
import com.biblioteca.constants.Constants;
import com.biblioteca.model.User;

import static com.biblioteca.constants.Constants.*;
import static com.biblioteca.constants.Constants.ENTER_LIBRARY_NO;

public class LoginController {

    private Login login;
    private View view;

    public LoginController(Login login, View view) {
        this.login = login;
        this.view = view;
    }

    public User execute() {
        view.displayMessage(ENTER_LIBRARY_NO);
        String libraryNo = view.getString();
        view.displayMessage(ENTER_PASSWORD);
        String password = view.getString();
        return login.login(libraryNo, password);
    }
}
