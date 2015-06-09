package com.biblioteca.command;

import com.biblioteca.console.BibliotecaConsoleIO;
import com.biblioteca.repository.Borrowables;

import static com.biblioteca.console.BibliotecaConsoleIO.*;

public class CheckoutCommand implements Command {

    private Borrowables borrowables;
    private BibliotecaConsoleIO bibliotecaConsoleIO;

    public CheckoutCommand(Borrowables borrowables, BibliotecaConsoleIO bibliotecaConsoleIO) {
        this.borrowables = borrowables;
        this.bibliotecaConsoleIO = bibliotecaConsoleIO;
    }

    @Override
    public void execute() {
        bibliotecaConsoleIO.displayMessage(CHECKOUT_PROMPT_TEXT);
        String bookName = bibliotecaConsoleIO.getBookNameFromUser();
        if (borrowables.checkout(bookName)) {
            bibliotecaConsoleIO.displayMessage(SUCCESSFULL_CHECKOUT_TEXT);
        } else {
            bibliotecaConsoleIO.displayMessage(BOOK_NOT_PRESENT_TEXT);
        }
    }

}
