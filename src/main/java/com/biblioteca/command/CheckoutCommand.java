package com.biblioteca.command;

import com.biblioteca.console.BibliotecaConsoleIO;
import com.biblioteca.repository.Books;

import static com.biblioteca.console.BibliotecaConsoleIO.*;

public class CheckoutCommand implements Command {

    private Books books;
    private BibliotecaConsoleIO bibliotecaConsoleIO;

    public CheckoutCommand(Books books, BibliotecaConsoleIO bibliotecaConsoleIO) {
        this.books = books;
        this.bibliotecaConsoleIO = bibliotecaConsoleIO;
    }

    @Override
    public void execute() {
        bibliotecaConsoleIO.displayMessage(CHECKOUT_PROMPT_TEXT);
        String bookName = bibliotecaConsoleIO.getBookNameFromUser();
        if (books.checkout(bookName)) {
            bibliotecaConsoleIO.displayMessage(SUCCESSFULL_CHECKOUT_TEXT);
        } else {
            bibliotecaConsoleIO.displayMessage(BOOK_NOT_PRESENT_TEXT);
        }
    }

}
