package com.biblioteca.app;

import com.biblioteca.command.CommandFactory;
import com.biblioteca.console.BibliotecaConsoleIO;
import com.biblioteca.dao.Books;
import com.biblioteca.enums.MenuItem;

import static com.biblioteca.enums.MenuItem.*;

public class BibliotecaApp {

    public static final String WELCOME_TEXT = "Hello! Welcome to Biblioteca";

    private String welcomeMessage;
    private Books books;
    private BibliotecaConsoleIO bibliotecaConsoleIO;
    private CommandFactory commandFactory;

    public BibliotecaApp(String welcomeMessage, Books books, BibliotecaConsoleIO bibliotecaConsoleIO,
                         CommandFactory commandFactory) {
        this.welcomeMessage = welcomeMessage;
        this.books = books;
        this.bibliotecaConsoleIO = bibliotecaConsoleIO;
        this.commandFactory = commandFactory;
    }

    public void start() {
        bibliotecaConsoleIO.displayMessage(welcomeMessage);
        do {
            bibliotecaConsoleIO.displayMenu();
            int userChoice = bibliotecaConsoleIO.getUserChoice();
            MenuItem selectedMenuItem = valueOf(userChoice);
            commandFactory.getCommand(selectedMenuItem).execute();
            if (selectedMenuItem == QUIT)
                break;
        } while (true);
    }

}
