package com.biblioteca.controller;

import com.biblioteca.action.Login;
import com.biblioteca.listener.Listener;
import com.biblioteca.model.User;
import com.biblioteca.view.View;

import static com.biblioteca.constants.Constants.*;

public class LoginController {

    private Login login;
    private View view;
    private Listener listener;

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
        listener.update(RUNNING);
        return user;
    }

    public void addListener(Listener listener) {
        this.listener = listener;
    }

}
