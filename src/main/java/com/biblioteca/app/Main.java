package com.biblioteca.app;

import com.biblioteca.command.CommandFactory;
import com.biblioteca.console.BibliotecaConsoleIO;
import com.biblioteca.dao.Books;

import java.util.Scanner;

import static com.biblioteca.console.BibliotecaConsoleIO.*;

public class Main {

    public static void main(String[] args) {
        BibliotecaApp bibliotecaApp = new BibliotecaApp(WELCOME_TEXT, new
                BibliotecaConsoleIO(new Scanner(System.in)), new CommandFactory(new Books()));
        bibliotecaApp.start();
    }

}
