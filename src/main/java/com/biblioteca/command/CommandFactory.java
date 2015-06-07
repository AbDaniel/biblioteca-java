package com.biblioteca.command;

import com.biblioteca.console.BibliotecaConsoleIO;
import com.biblioteca.dao.Books;
import com.biblioteca.enums.MenuItem;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static com.biblioteca.command.QuitCommand.EXIT_MESSAGE;
import static com.biblioteca.enums.MenuItem.CHECKOUT_BOOK;
import static com.biblioteca.enums.MenuItem.LIST_BOOKS;
import static com.biblioteca.enums.MenuItem.QUIT;

public class CommandFactory {

    private Map<MenuItem, Command> commandMap = new HashMap<>();

    public CommandFactory(Books books) {
        commandMap.put(LIST_BOOKS, new ListBooksCommand(books, new BibliotecaConsoleIO(new Scanner
                (System.in))));
        commandMap.put(CHECKOUT_BOOK, new CheckoutCommand(books, new BibliotecaConsoleIO(new Scanner
                (System.in))));
        commandMap.put(QUIT, new QuitCommand(EXIT_MESSAGE));
    }

    public Command getCommand(MenuItem menuItem) {
        return commandMap.get(menuItem);
    }

}
