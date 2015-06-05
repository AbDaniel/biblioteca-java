package com.biblioteca.app;

import com.biblioteca.console.BibliotecaConsoleIO;
import com.biblioteca.dao.Books;

public class Main {

    public static void main(String[] args) {
        BibliotecaApp bibliotecaApp = new BibliotecaApp(BibliotecaApp.WELCOME_TEXT, Books.getInstance(), new
                BibliotecaConsoleIO());
        bibliotecaApp.start();
    }

}
