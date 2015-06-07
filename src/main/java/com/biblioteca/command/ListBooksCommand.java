package com.biblioteca.command;

import com.biblioteca.console.BibliotecaConsoleIO;
import com.biblioteca.repository.Books;

public class ListBooksCommand implements Command {

    private final Books books;
    private final BibliotecaConsoleIO bibliotecaConsoleIO;

    public ListBooksCommand(Books books, BibliotecaConsoleIO bibliotecaConsoleIO) {
        this.books = books;
        this.bibliotecaConsoleIO = bibliotecaConsoleIO;
    }

    @Override
    public void execute() {
        bibliotecaConsoleIO.displayListOfBooks(books.allAvailableBooks());
    }

}
