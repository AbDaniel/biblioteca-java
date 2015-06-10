package com.biblioteca.command;

import com.biblioteca.console.BibliotecaConsoleIO;
import com.biblioteca.model.Owner;
import com.biblioteca.repository.Borrowables;
import com.biblioteca.enums.MenuItem;

import java.util.HashMap;
import java.util.Map;

import static com.biblioteca.constants.Constants.EXIT_MESSAGE;
import static com.biblioteca.enums.MenuItem.*;

public class Actions {

    private final Owner owner;
    private Map<MenuItem, Action> commandMap = new HashMap<>();

    public Actions(Borrowables borrowables, BibliotecaConsoleIO bibliotecaConsoleIO, Owner owner) {
        this.owner = owner;
        commandMap.put(LIST_BOOKS, new ListBooks(borrowables, bibliotecaConsoleIO));
        commandMap.put(CHECKOUT_BOOK, new Checkout(borrowables, bibliotecaConsoleIO, this.owner));
        commandMap.put(RETURN_BOOK, new Return(borrowables, bibliotecaConsoleIO, this.owner));
        commandMap.put(QUIT, new Quit(EXIT_MESSAGE));
    }

    public Action getCommand(MenuItem menuItem) {
        return commandMap.get(menuItem);
    }

}
