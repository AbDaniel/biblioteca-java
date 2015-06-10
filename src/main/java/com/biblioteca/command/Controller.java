package com.biblioteca.command;

import com.biblioteca.console.BibliotecaConsoleIO;
import com.biblioteca.enums.MenuItem;

import static com.biblioteca.constants.Constants.INVALID_INPUT;
import static com.biblioteca.constants.Constants.INVALID_INPUT_TEXT;
import static com.biblioteca.enums.MenuItem.valueOf;

public class Controller {

    BibliotecaConsoleIO bibliotecaConsoleIO;
    private Actions actions;

    public Controller(BibliotecaConsoleIO bibliotecaConsoleIO, Actions actions) {
        this.bibliotecaConsoleIO = bibliotecaConsoleIO;
        this.actions = actions;
    }

    public boolean execute() {
        boolean status = true;
        bibliotecaConsoleIO.displayMenu();
        int userChoice = bibliotecaConsoleIO.getUserChoice();
        if (userChoice != INVALID_INPUT) {
            status = actions.execute(userChoice);
        } else {
            bibliotecaConsoleIO.displayMessage(INVALID_INPUT_TEXT);
        }
        return status;
    }

}
