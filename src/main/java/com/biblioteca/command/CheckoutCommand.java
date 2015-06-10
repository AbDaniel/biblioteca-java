package com.biblioteca.command;

import com.biblioteca.console.BibliotecaConsoleIO;
import com.biblioteca.constants.Constants;
import com.biblioteca.model.Owner;
import com.biblioteca.repository.Borrowables;

public class CheckoutCommand implements Command {

    private Borrowables borrowables;
    private BibliotecaConsoleIO bibliotecaConsoleIO;
    private Owner owner;

    public CheckoutCommand(Borrowables borrowables, BibliotecaConsoleIO bibliotecaConsoleIO, Owner owner) {
        this.borrowables = borrowables;
        this.bibliotecaConsoleIO = bibliotecaConsoleIO;
        this.owner = owner;
    }

    @Override
    public void execute() {
        bibliotecaConsoleIO.displayMessage(Constants.CHECKOUT_PROMPT_TEXT);
        String bookName = bibliotecaConsoleIO.getBookNameFromUser();
        if (borrowables.checkout(bookName, owner)) {
            bibliotecaConsoleIO.displayMessage(Constants.SUCCESSFUL_CHECKOUT_TEXT);
        } else {
            bibliotecaConsoleIO.displayMessage(Constants.BOOK_NOT_PRESENT_TEXT);
        }
    }

}
