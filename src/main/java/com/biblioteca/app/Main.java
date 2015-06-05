package com.biblioteca.app;

import com.biblioteca.console.BibliotecaConsoleIO;
import com.biblioteca.dao.BookRepository;

public class Main {

    public static void main(String[] args) {
        BibliotecaApp bibliotecaApp = new BibliotecaApp(BibliotecaApp.WELCOME_TEXT, BookRepository.getInstance(), new
                BibliotecaConsoleIO());
        bibliotecaApp.start();
    }

}
