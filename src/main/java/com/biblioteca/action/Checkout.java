package com.biblioteca.action;

import com.biblioteca.model.User;
import com.biblioteca.view.View;
import com.biblioteca.repository.Library;

import static com.biblioteca.constants.Constants.*;
import static com.biblioteca.constants.Constants.SUCCESSFUL_CHECKOUT_TEXT;

public class Checkout implements Action {

    private Library library;
    private View view;
    private User user;

    public Checkout(Library library, View view, User user) {
        this.library = library;
        this.view = view;
        this.user = user;
    }

    @Override
    public void execute() {
        view.displayMessage(CHECKOUT_PROMPT_TEXT);
        String bookName = view.getString();
        if (library.checkout(bookName, user)) {
            view.displayMessage(SUCCESSFUL_CHECKOUT_TEXT);
        } else {
            view.displayMessage(BOOK_NOT_PRESENT_TEXT);
        }
    }

}
