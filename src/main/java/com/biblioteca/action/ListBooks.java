package com.biblioteca.action;

import com.biblioteca.console.BibliotecaConsoleIO;
import com.biblioteca.repository.Borrowables;

public class ListBooks implements Action {

    private final Borrowables borrowables;
    private final BibliotecaConsoleIO bibliotecaConsoleIO;

    public ListBooks(Borrowables borrowables, BibliotecaConsoleIO bibliotecaConsoleIO) {
        this.borrowables = borrowables;
        this.bibliotecaConsoleIO = bibliotecaConsoleIO;
    }

    @Override
    public void execute() {
        bibliotecaConsoleIO.displayListOfBooks(borrowables.allAvailableItems());
    }

}
