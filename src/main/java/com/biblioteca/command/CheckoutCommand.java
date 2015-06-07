package com.biblioteca.command;

import com.biblioteca.console.BibliotecaConsoleIO;
import com.biblioteca.repository.Books;
import com.biblioteca.model.Book;

import static com.biblioteca.console.BibliotecaConsoleIO.CHECKOUT_PROMPT_TEXT;
import static com.biblioteca.console.BibliotecaConsoleIO.BOOK_NOT_PRESENT_TEXT;
import static com.biblioteca.console.BibliotecaConsoleIO.SUCCESSFULL_CHECKOUT_TEXT;

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

        } else {
            bibliotecaConsoleIO.displayMessage(BOOK_NOT_PRESENT_TEXT);
        }
    }

}
