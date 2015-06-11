package com.biblioteca.controller;

import com.biblioteca.action.Login;
import com.biblioteca.view.View;
import com.biblioteca.model.User;

import static com.biblioteca.constants.Constants.*;
import static com.biblioteca.constants.Constants.ENTER_LIBRARY_NO;
import static com.biblioteca.constants.Constants.ENTER_PASSWORD;

public class LoginController {

    private Login login;
    private View view;

    public LoginController(Login login, View view) {
        this.login = login;
        this.view = view;
    }

    public User execute() {
        User user;
        do {
            view.displayMessage(ENTER_LIBRARY_NO);
            String libraryNo = view.getString();
            view.displayMessage(ENTER_PASSWORD);
            String password = view.getString();
            user = login.login(libraryNo, password);
            if (user == null) {
                view.displayMessage(INVALID_CREDENTIALS);
            }
        } while (user == null);
        return user;
    }

}
