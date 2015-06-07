package com.biblioteca.command;

import com.biblioteca.console.BibliotecaConsoleIO;
import com.biblioteca.model.Book;
import com.biblioteca.repository.Books;

import static com.biblioteca.console.BibliotecaConsoleIO.CHECKOUT_PROMPT_TEXT;

public class ReturnCommand implements Command {

    private Books books;
    private BibliotecaConsoleIO bibliotecaConsoleIO;

    public ReturnCommand(Books books, BibliotecaConsoleIO bibliotecaConsoleIO) {
        this.books = books;
        this.bibliotecaConsoleIO = bibliotecaConsoleIO;
    }

    @Override
    public void execute() {
        bibliotecaConsoleIO.displayMessage(CHECKOUT_PROMPT_TEXT);
        String bookName = bibliotecaConsoleIO.getBookNameFromUser();
        Book book = books.findInCheckedOutBooks(bookName);
    }

}
