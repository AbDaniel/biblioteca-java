package com.biblioteca.command;

import com.biblioteca.console.BibliotecaConsoleIO;
import com.biblioteca.enums.MenuItem;

import static com.biblioteca.constants.Constants.INVALID_INPUT;
import static com.biblioteca.constants.Constants.INVALID_INPUT_TEXT;
import static com.biblioteca.enums.MenuItem.valueOf;

public class ExecuteUserOptionAction implements Action {

    BibliotecaConsoleIO bibliotecaConsoleIO;
    private Actions actions;

    public ExecuteUserOptionAction(BibliotecaConsoleIO bibliotecaConsoleIO, Actions actions) {
        this.bibliotecaConsoleIO = bibliotecaConsoleIO;
        this.actions = actions;
    }

    @Override
    public void execute() {
        bibliotecaConsoleIO.displayMenu();
        int userChoice = bibliotecaConsoleIO.getUserChoice();
        if (userChoice != INVALID_INPUT) {
            MenuItem selectedMenuItem = valueOf(userChoice);
            actions.getCommand(selectedMenuItem).execute();
        } else {
            bibliotecaConsoleIO.displayMessage(INVALID_INPUT_TEXT);
        }
    }

}
