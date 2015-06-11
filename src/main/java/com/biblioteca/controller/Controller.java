package com.biblioteca.controller;

import com.biblioteca.action.Dispatcher;
import com.biblioteca.console.BibliotecaConsoleIO;
import com.biblioteca.model.Owner;

import static com.biblioteca.constants.Constants.INVALID_INPUT;
import static com.biblioteca.constants.Constants.INVALID_INPUT_TEXT;
import static com.biblioteca.enums.MenuItem.valueOf;

public class Controller {

    BibliotecaConsoleIO bibliotecaConsoleIO;
    private Dispatcher dispatcher;

    public Controller(BibliotecaConsoleIO bibliotecaConsoleIO, Dispatcher dispatcher) {
        this.bibliotecaConsoleIO = bibliotecaConsoleIO;
        this.dispatcher = dispatcher;
    }

    public boolean execute(Owner owner) {
        boolean status = true;
        bibliotecaConsoleIO.displayMenu();
        int userChoice = bibliotecaConsoleIO.getUserChoice();
        if (userChoice != INVALID_INPUT) {
            status = dispatcher.dispatch(userChoice, owner);
        } else {
            bibliotecaConsoleIO.displayMessage(INVALID_INPUT_TEXT);
        }
        return status;
    }

}
