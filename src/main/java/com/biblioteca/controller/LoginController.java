package com.biblioteca.controller;

import com.biblioteca.action.Login;
import com.biblioteca.console.BibliotecaConsoleIO;
import com.biblioteca.constants.Constants;
import com.biblioteca.model.User;

import static com.biblioteca.constants.Constants.ENTER_USER_NAME;

public class LoginController {

    private Login login;
    private BibliotecaConsoleIO bibliotecaConsoleIO;

    public LoginController(Login login, BibliotecaConsoleIO bibliotecaConsoleIO) {
        this.login = login;
        this.bibliotecaConsoleIO = bibliotecaConsoleIO;
    }

    public User execute() {
        bibliotecaConsoleIO.displayMessage(ENTER_USER_NAME);
        return null;
    }
}
