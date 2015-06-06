package com.biblioteca.app;

import com.biblioteca.command.CommandFactory;
import com.biblioteca.console.BibliotecaConsoleIO;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        BibliotecaApp bibliotecaApp = new BibliotecaApp(BibliotecaApp.WELCOME_TEXT, new
                BibliotecaConsoleIO(new Scanner(System.in)), new CommandFactory());
        bibliotecaApp.start();
    }

}
