package com.biblioteca.app;

import com.biblioteca.console.BibliotecaConsoleIO;
import com.biblioteca.dao.Books;
import com.biblioteca.enums.MenuItem;

import static com.biblioteca.enums.MenuItem.*;

public class BibliotecaApp {

    public static final String WELCOME_TEXT = "Hello! Welcome to Biblioteca";

    private String welcomeMessage;
    private Books books;
    private BibliotecaConsoleIO bibliotecaConsoleIO;

    public BibliotecaApp(String welcomeMessage, Books books, BibliotecaConsoleIO bibliotecaConsoleIO) {
        this.welcomeMessage = welcomeMessage;
        this.books = books;
        this.bibliotecaConsoleIO = bibliotecaConsoleIO;
    }

    public void start() {
        bibliotecaConsoleIO.displayWelcomeMessage(welcomeMessage);
        bibliotecaConsoleIO.displayMenu();
        MenuItem selectedMenuItem = bibliotecaConsoleIO.getMenuItemFromConsole();
        bibliotecaConsoleIO.displayListOfBooks(books.all());
    }

}
