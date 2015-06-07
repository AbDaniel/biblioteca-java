package com.biblioteca.command;

import com.biblioteca.console.BibliotecaConsoleIO;
import com.biblioteca.dao.Books;
import com.biblioteca.enums.MenuItem;

import java.util.HashMap;
import java.util.Map;

import static com.biblioteca.command.QuitCommand.EXIT_MESSAGE;
import static com.biblioteca.enums.MenuItem.*;

public class CommandFactory {

    private Map<MenuItem, Command> commandMap = new HashMap<>();

    public CommandFactory(Books books, BibliotecaConsoleIO bibliotecaConsoleIO) {
        commandMap.put(LIST_BOOKS, new ListBooksCommand(books, bibliotecaConsoleIO));
        commandMap.put(CHECKOUT_BOOK, new CheckoutCommand(books, bibliotecaConsoleIO));
        commandMap.put(QUIT, new QuitCommand(EXIT_MESSAGE));
    }

    public Command getCommand(MenuItem menuItem) {
        return commandMap.get(menuItem);
    }

}
