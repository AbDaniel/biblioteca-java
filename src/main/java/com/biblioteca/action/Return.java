package com.biblioteca.action;

import com.biblioteca.console.BibliotecaConsoleIO;
import com.biblioteca.constants.Constants;
import com.biblioteca.model.Owner;
import com.biblioteca.repository.Borrowables;

public class Return implements Action {

    private Borrowables borrowables;
    private BibliotecaConsoleIO bibliotecaConsoleIO;

    public Return(Borrowables borrowables, BibliotecaConsoleIO bibliotecaConsoleIO) {
        this.borrowables = borrowables;
        this.bibliotecaConsoleIO = bibliotecaConsoleIO;
    }

    @Override
    public void execute(Owner owner) {
        bibliotecaConsoleIO.displayMessage(Constants.CHECKOUT_PROMPT_TEXT);
        String bookName = bibliotecaConsoleIO.getBookNameFromUser();
        if (borrowables.returnItem(bookName, owner)) {
            bibliotecaConsoleIO.displayMessage(Constants.SUCCESSFUL_RETURN_TEXT);
        } else {
            bibliotecaConsoleIO.displayMessage(Constants.BOOK_NOT_VALID_TEXT);
        }
    }

}
