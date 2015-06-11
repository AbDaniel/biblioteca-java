package com.biblioteca.action;

import com.biblioteca.console.View;
import com.biblioteca.constants.Constants;
import com.biblioteca.model.Owner;
import com.biblioteca.repository.Borrowables;

public class Checkout implements Action {

    private Borrowables borrowables;
    private View view;

    public Checkout(Borrowables borrowables, View view) {
        this.borrowables = borrowables;
        this.view = view;
    }

    @Override
    public void execute(Owner owner) {
        view.displayMessage(Constants.CHECKOUT_PROMPT_TEXT);
        String bookName = view.getString();
        if (borrowables.checkout(bookName, owner)) {
            view.displayMessage(Constants.SUCCESSFUL_CHECKOUT_TEXT);
        } else {
            view.displayMessage(Constants.BOOK_NOT_PRESENT_TEXT);
        }
    }

}
