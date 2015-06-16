package com.biblioteca.controller;

import com.biblioteca.action.Login;
import com.biblioteca.listener.ExitLogoutListener;
import com.biblioteca.listener.LoginListener;
import com.biblioteca.view.View;
import com.biblioteca.model.User;

import java.util.Map;

import static com.biblioteca.constants.Constants.*;
import static com.biblioteca.constants.Constants.ENTER_LIBRARY_NO;
import static com.biblioteca.constants.Constants.ENTER_PASSWORD;

public class LoginController {

    private Login login;
    private View view;
    private Map<User, Controller> controllers;
    private ExitLogoutListener exitLogoutListener;
    private LoginListener loginListener;

    public LoginController(Login login, View view, Map<User, Controller> controllers) {
        this.login = login;
        this.view = view;
        this.controllers = controllers;
    }

    public void execute() {
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
        Controller controller = controllers.get(user);
        exitLogoutListener.update(RUNNING);
        loginListener.update(user, controller);
    }

    public void addExitLogoutListener(ExitLogoutListener listener) {
        this.exitLogoutListener = listener;
    }

    public void addLoginListener(LoginListener loginListener) {
        this.loginListener = loginListener;
    }

}
