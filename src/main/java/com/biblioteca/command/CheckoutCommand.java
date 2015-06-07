package com.biblioteca.command;

import com.biblioteca.console.BibliotecaConsoleIO;
import com.biblioteca.dao.Books;
import com.biblioteca.model.Book;

import static com.biblioteca.console.BibliotecaConsoleIO.BOOK_NAME_TEXT;

public class CheckoutCommand implements Command {

    private Books books;
    private BibliotecaConsoleIO bibliotecaConsoleIO;

    public CheckoutCommand(Books books, BibliotecaConsoleIO bibliotecaConsoleIO) {
        this.books = books;
        this.bibliotecaConsoleIO = bibliotecaConsoleIO;
    }

    @Override
    public void execute() {
        bibliotecaConsoleIO.displayMessage(BOOK_NAME_TEXT);
        Book book = books.findByName("Lord of the Rings");
    }
}
