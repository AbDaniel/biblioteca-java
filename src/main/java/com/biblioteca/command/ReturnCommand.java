package com.biblioteca.command;

import com.biblioteca.console.BibliotecaConsoleIO;
import com.biblioteca.model.Owner;
import com.biblioteca.repository.Borrowables;

import static com.biblioteca.console.BibliotecaConsoleIO.*;

public class ReturnCommand implements Command {

    private Borrowables borrowables;
    private BibliotecaConsoleIO bibliotecaConsoleIO;
    private Owner owner;

    public ReturnCommand(Borrowables borrowables, BibliotecaConsoleIO bibliotecaConsoleIO) {
        this.borrowables = borrowables;
        this.bibliotecaConsoleIO = bibliotecaConsoleIO;
    }

    @Override
    public void execute() {
        bibliotecaConsoleIO.displayMessage(CHECKOUT_PROMPT_TEXT);
        String bookName = bibliotecaConsoleIO.getBookNameFromUser();
        if (borrowables.returnItem(bookName, owner)) {
            bibliotecaConsoleIO.displayMessage(SUCCESSFULL_RETURN_TEXT);
        } else {
            bibliotecaConsoleIO.displayMessage(BOOK_NOT_VALID_TEXT);
        }
    }

}
