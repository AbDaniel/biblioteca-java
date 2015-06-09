package com.biblioteca.app;

import com.biblioteca.command.CommandFactory;
import com.biblioteca.console.BibliotecaConsoleIO;
import com.biblioteca.model.Ownable;
import com.biblioteca.model.Owner;
import com.biblioteca.repository.Borrowables;
import com.biblioteca.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.biblioteca.console.BibliotecaConsoleIO.*;
import static java.lang.System.*;

public class Main {

    public static void main(String[] args) {
        List<Book> availableBooks = new ArrayList<>();
        availableBooks.add(new Book("Lord of the Rings", "JR Toliken", 1930));
        availableBooks.add(new Book("Harry Potter", "JK Rowling", 1992));
        availableBooks.add(new Book("Catch-22", "Joesph Heller", 1950));
        availableBooks.add(new Book("Winds of Winter", "George RR Martin", 2017));
        List<Book> checkedOutBooks = new ArrayList<>();

        BibliotecaConsoleIO bibliotecaConsoleIO = new BibliotecaConsoleIO(new Scanner(in));
        Owner owner = new Owner() {
            @Override
            public void addOwnable(Ownable ownable) {

            }

            @Override
            public void removeOwnable(Ownable ownable) {

            }
        };
        CommandFactory commandFactory = new CommandFactory(new Borrowables<>(availableBooks), bibliotecaConsoleIO, owner);

        BibliotecaApp bibliotecaApp = new BibliotecaApp(WELCOME_TEXT, bibliotecaConsoleIO, commandFactory);
        bibliotecaApp.start();
    }

}
