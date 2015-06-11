package com.biblioteca.controller;

import com.biblioteca.action.Dispatcher;
import com.biblioteca.console.BibliotecaConsoleIO;
import com.biblioteca.enums.MenuItem;
import com.biblioteca.model.Owner;

import static com.biblioteca.constants.Constants.INVALID_INPUT;
import static com.biblioteca.constants.Constants.INVALID_INPUT_TEXT;
import static com.biblioteca.enums.MenuItem.QUIT;
import static com.biblioteca.enums.MenuItem.valueOf;

public class Controller {

    BibliotecaConsoleIO bibliotecaConsoleIO;
    private Dispatcher dispatcher;

    public Controller(BibliotecaConsoleIO bibliotecaConsoleIO, Dispatcher dispatcher) {
        this.bibliotecaConsoleIO = bibliotecaConsoleIO;
        this.dispatcher = dispatcher;
    }

    public boolean execute(Owner owner) {
        bibliotecaConsoleIO.displayMenu();
        int userChoice = bibliotecaConsoleIO.getUserChoice();

        MenuItem selectedMenuItem = valueOf(userChoice);
        if (selectedMenuItem == QUIT)
            return false;
        dispatcher.dispatch(selectedMenuItem, owner);
        return true;
    }

    public interface ExitListener {
        void update(int userChoice);
    }

}
