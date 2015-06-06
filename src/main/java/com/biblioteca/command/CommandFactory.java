package com.biblioteca.command;

import com.biblioteca.console.BibliotecaConsoleIO;
import com.biblioteca.dao.Books;
import com.biblioteca.enums.MenuItem;

import java.util.HashMap;
import java.util.Map;

import static com.biblioteca.command.QuitCommand.EXIT_MESSAGE;
import static com.biblioteca.enums.MenuItem.LIST_BOOKS;
import static com.biblioteca.enums.MenuItem.QUIT;

public class CommandFactory {

    private Map<MenuItem, Command> commandMap = new HashMap<>();

    public CommandFactory() {
        commandMap.put(LIST_BOOKS, new ListBooksCommand(Books.getInstance(), new BibliotecaConsoleIO()));
        commandMap.put(QUIT, new QuitCommand(EXIT_MESSAGE));
    }

    public Command getCommand(MenuItem menuItem) {
        return commandMap.get(menuItem);
    }

}
