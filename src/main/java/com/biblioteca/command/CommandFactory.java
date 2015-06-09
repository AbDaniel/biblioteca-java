package com.biblioteca.command;

import com.biblioteca.console.BibliotecaConsoleIO;
import com.biblioteca.repository.Borrowables;
import com.biblioteca.enums.MenuItem;

import java.util.HashMap;
import java.util.Map;

import static com.biblioteca.command.QuitCommand.EXIT_MESSAGE;
import static com.biblioteca.enums.MenuItem.*;

public class CommandFactory {

    private Map<MenuItem, Command> commandMap = new HashMap<>();

    public CommandFactory(Borrowables borrowables, BibliotecaConsoleIO bibliotecaConsoleIO) {
        commandMap.put(LIST_BOOKS, new ListBooksCommand(borrowables, bibliotecaConsoleIO));
        commandMap.put(CHECKOUT_BOOK, new CheckoutCommand(borrowables, bibliotecaConsoleIO));
        commandMap.put(RETURN_BOOK, new ReturnCommand(borrowables, bibliotecaConsoleIO));
        commandMap.put(QUIT, new QuitCommand(EXIT_MESSAGE));
    }

    public Command getCommand(MenuItem menuItem) {
        return commandMap.get(menuItem);
    }

}
