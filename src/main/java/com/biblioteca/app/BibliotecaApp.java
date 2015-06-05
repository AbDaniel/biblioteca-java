package com.biblioteca.app;

import com.biblioteca.console.BibliotecaConsoleIO;
import com.biblioteca.dao.BookDAO;

public class BibliotecaApp {

    public static final String WELCOME_TEXT = "Hello! Welcome to Biblioteca";

    private String welcomeMessage;
    private BookDAO bookDAO;
    private BibliotecaConsoleIO bibliotecaConsoleIO;

    public BibliotecaApp(String welcomeMessage, BookDAO bookDAO, BibliotecaConsoleIO bibliotecaConsoleIO) {
        this.welcomeMessage = welcomeMessage;
        this.bookDAO = bookDAO;
        this.bibliotecaConsoleIO = bibliotecaConsoleIO;
    }

    public void start() {
        bibliotecaConsoleIO.displayWelcomeMessage(welcomeMessage);
        bibliotecaConsoleIO.displayListOfBooks(bookDAO.books());
    }

}
