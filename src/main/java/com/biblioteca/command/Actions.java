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
    private Map<MenuItem, Action> commandMap;

    public Actions(Borrowables borrowables, BibliotecaConsoleIO bibliotecaConsoleIO, Owner owner, Map<MenuItem, Action> commandMap) {
        this.owner = owner;
        this.commandMap = commandMap;
        this.commandMap.put(LIST_BOOKS, new ListBooks(borrowables, bibliotecaConsoleIO));
        this.commandMap.put(CHECKOUT_BOOK, new Checkout(borrowables, bibliotecaConsoleIO, this.owner));
        this.commandMap.put(RETURN_BOOK, new Return(borrowables, bibliotecaConsoleIO, this.owner));
    }

    public boolean execute(int userChoice) {
        MenuItem selectedMenuItem = valueOf(userChoice);
        if (selectedMenuItem == QUIT)
            return false;
        commandMap.get(selectedMenuItem).execute();
        return true;
    }

}
