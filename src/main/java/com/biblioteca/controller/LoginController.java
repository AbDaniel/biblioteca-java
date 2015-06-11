package com.biblioteca.controller;

import com.biblioteca.action.Login;
import com.biblioteca.console.View;
import com.biblioteca.model.User;

import static com.biblioteca.constants.Constants.ENTER_USER_NAME;

public class LoginController {

    private Login login;
    private View view;

    public LoginController(Login login, View view) {
        this.login = login;
        this.view = view;
    }

    public User execute() {
        view.displayMessage(ENTER_USER_NAME);
        return null;
    }
}
