package com.biblioteca.command;

import com.biblioteca.console.BibliotecaConsoleIO;
import com.biblioteca.dao.Books;
import com.biblioteca.model.Book;

import static com.biblioteca.console.BibliotecaConsoleIO.BOOK_NAME_TEXT;
import static com.biblioteca.console.BibliotecaConsoleIO.BOOK_NOT_PRESENT_TEXT;

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
        String bookName = bibliotecaConsoleIO.getBookNameFromUser();
        Book book = books.findByName(bookName);
        if (book == null) {
            bibliotecaConsoleIO.displayMessage(BOOK_NOT_PRESENT_TEXT);
        }
    }
}
