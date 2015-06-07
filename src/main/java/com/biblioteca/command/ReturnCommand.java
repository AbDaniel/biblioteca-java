package com.biblioteca.command;

import com.biblioteca.console.BibliotecaConsoleIO;
import com.biblioteca.model.Book;
import com.biblioteca.repository.Books;

import static com.biblioteca.console.BibliotecaConsoleIO.*;

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
        if (book == null) {
            bibliotecaConsoleIO.displayMessage(BOOK_NOT_VALID_TEXT);
        } else {
            books.returnBook(book);
            bibliotecaConsoleIO.displayMessage(SUCCESSFULL_RETURN_TEXT);
        }
    }

}
