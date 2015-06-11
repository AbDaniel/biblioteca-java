package com.biblioteca.action;

import com.biblioteca.view.View;
import com.biblioteca.model.Owner;
import com.biblioteca.repository.Borrowables;

import static com.biblioteca.constants.Constants.*;
import static com.biblioteca.constants.Constants.SUCCESSFUL_CHECKOUT_TEXT;

public class Checkout implements Action {

    private Borrowables borrowables;
    private View view;

    public Checkout(Borrowables borrowables, View view) {
        this.borrowables = borrowables;
        this.view = view;
    }

    @Override
    public void execute(Owner owner) {
        view.displayMessage(CHECKOUT_PROMPT_TEXT);
        String bookName = view.getString();
        if (borrowables.checkout(bookName, owner)) {
            view.displayMessage(SUCCESSFUL_CHECKOUT_TEXT);
        } else {
            view.displayMessage(BOOK_NOT_PRESENT_TEXT);
        }
    }

}
