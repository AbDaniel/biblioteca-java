package com.biblioteca.command;

import com.biblioteca.console.BibliotecaConsoleIO;
import com.biblioteca.repository.Borrowables;

public class ListBooksCommand implements Command {

    private final Borrowables borrowables;
    private final BibliotecaConsoleIO bibliotecaConsoleIO;

    public ListBooksCommand(Borrowables borrowables, BibliotecaConsoleIO bibliotecaConsoleIO) {
        this.borrowables = borrowables;
        this.bibliotecaConsoleIO = bibliotecaConsoleIO;
    }

    @Override
    public void execute() {
        bibliotecaConsoleIO.displayListOfBooks(borrowables.allAvailableBooks());
    }

}
