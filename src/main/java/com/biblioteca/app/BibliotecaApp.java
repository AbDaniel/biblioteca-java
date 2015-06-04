package com.biblioteca.app;

import com.biblioteca.dao.BookDAO;

public class BibliotecaApp {

    public static final String WELCOME_TEXT = "Hello! Welcome to Biblioteca";

    private String welcomeMessage;
    private BookDAO bookDAO;

    public BibliotecaApp(String welcomeMessage, BookDAO bookDAO) {
        this.welcomeMessage = welcomeMessage;
        this.bookDAO = bookDAO;
    }

    void start() {
        System.out.println(welcomeMessage);
    }

}
