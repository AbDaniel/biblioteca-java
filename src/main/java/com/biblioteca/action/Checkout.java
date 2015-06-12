package com.biblioteca.action;

import com.biblioteca.view.View;
import com.biblioteca.model.Owner;
import com.biblioteca.repository.Library;

import static com.biblioteca.constants.Constants.*;
import static com.biblioteca.constants.Constants.SUCCESSFUL_CHECKOUT_TEXT;

public class Checkout implements Action {

    private Library library;
    private View view;

    public Checkout(Library library, View view) {
        this.library = library;
        this.view = view;
    }

    @Override
    public void execute(Owner owner) {
        view.displayMessage(CHECKOUT_PROMPT_TEXT);
        String bookName = view.getString();
        if (library.checkout(bookName, owner)) {
            view.displayMessage(SUCCESSFUL_CHECKOUT_TEXT);
        } else {
            view.displayMessage(BOOK_NOT_PRESENT_TEXT);
        }
    }

}
