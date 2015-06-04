package com.biblioteca.app;

import com.biblioteca.dao.BookDAO;

public class Main {

    public static void main( String[] args) {
        BibliotecaApp bibliotecaApp = new BibliotecaApp(BibliotecaApp.WELCOME_TEXT, BookDAO.getInstance());
    }

}
