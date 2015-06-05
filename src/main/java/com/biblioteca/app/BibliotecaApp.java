package com.biblioteca.app;

import com.biblioteca.console.BibliotecaConsoleIO;
import com.biblioteca.dao.BookRepository;

public class BibliotecaApp {

    public static final String WELCOME_TEXT = "Hello! Welcome to Biblioteca";

    private String welcomeMessage;
    private BookRepository bookRepository;
    private BibliotecaConsoleIO bibliotecaConsoleIO;

    public BibliotecaApp(String welcomeMessage, BookRepository bookRepository, BibliotecaConsoleIO bibliotecaConsoleIO) {
        this.welcomeMessage = welcomeMessage;
        this.bookRepository = bookRepository;
        this.bibliotecaConsoleIO = bibliotecaConsoleIO;
    }

    public void start() {
        bibliotecaConsoleIO.displayWelcomeMessage(welcomeMessage);
        bibliotecaConsoleIO.displayListOfBooks(bookRepository.books());
    }

}
