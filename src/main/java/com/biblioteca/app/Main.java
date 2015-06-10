package com.biblioteca.app;

import com.biblioteca.action.*;
import com.biblioteca.console.BibliotecaConsoleIO;
import com.biblioteca.constants.Constants;
import com.biblioteca.controller.Controller;
import com.biblioteca.enums.MenuItem;
import com.biblioteca.repository.Borrowables;
import com.biblioteca.model.Book;

import java.util.*;

import static com.biblioteca.enums.MenuItem.CHECKOUT_BOOK;
import static com.biblioteca.enums.MenuItem.LIST_BOOKS;
import static com.biblioteca.enums.MenuItem.RETURN_BOOK;
import static java.lang.System.*;

public class Main {

    public static void main(String[] args) {
        List<Book> availableBooks = new ArrayList<>();
        availableBooks.add(new Book("Lord of the Rings", "JR Toliken", 1930));
        availableBooks.add(new Book("Harry Potter", "JK Rowling", 1992));
        availableBooks.add(new Book("Catch-22", "Joesph Heller", 1950));
        availableBooks.add(new Book("Winds of Winter", "George RR Martin", 2017));

        BibliotecaConsoleIO bibliotecaConsoleIO = new BibliotecaConsoleIO(new Scanner(in));

        Borrowables<Book> borrowables = new Borrowables<>(availableBooks);

        Map<MenuItem, Action> actionMap = new HashMap<>();
        actionMap.put(LIST_BOOKS, new ListBooks(borrowables, bibliotecaConsoleIO));
        actionMap.put(CHECKOUT_BOOK, new Checkout(borrowables, bibliotecaConsoleIO));
        actionMap.put(RETURN_BOOK, new Return(borrowables, bibliotecaConsoleIO));

        Actions actions = new Actions(new HashMap<>());
        Controller controller = new Controller(bibliotecaConsoleIO, actions);

        BibliotecaApp bibliotecaApp = new BibliotecaApp(Constants.WELCOME_TEXT, bibliotecaConsoleIO, controller);
        bibliotecaApp.start();
    }

}
