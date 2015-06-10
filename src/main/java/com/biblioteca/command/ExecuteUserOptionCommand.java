package com.biblioteca.command;

import com.biblioteca.console.BibliotecaConsoleIO;
import com.biblioteca.enums.MenuItem;

import static com.biblioteca.constants.Constants.INVALID_INPUT;
import static com.biblioteca.constants.Constants.INVALID_INPUT_TEXT;
import static com.biblioteca.enums.MenuItem.valueOf;

public class ExecuteUserOptionCommand implements Command {

    BibliotecaConsoleIO bibliotecaConsoleIO;
    private CommandFactory commandFactory;

    public ExecuteUserOptionCommand(BibliotecaConsoleIO bibliotecaConsoleIO, CommandFactory commandFactory) {
        this.bibliotecaConsoleIO = bibliotecaConsoleIO;
        this.commandFactory = commandFactory;
    }

    @Override
    public void execute() {
        bibliotecaConsoleIO.displayMenu();
        int userChoice = bibliotecaConsoleIO.getUserChoice();
        if (userChoice != INVALID_INPUT) {
            MenuItem selectedMenuItem = valueOf(userChoice);
            commandFactory.getCommand(selectedMenuItem).execute();
        } else {
            bibliotecaConsoleIO.displayMessage(INVALID_INPUT_TEXT);
        }
    }

}
