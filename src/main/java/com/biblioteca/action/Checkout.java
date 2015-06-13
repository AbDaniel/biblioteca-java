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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Checkout checkout = (Checkout) o;

        if (library != null ? !library.equals(checkout.library) : checkout.library != null) return false;
        return !(user != null ? !user.equals(checkout.user) : checkout.user != null);

    }

    @Override
    public int hashCode() {
        int result = library != null ? library.hashCode() : 0;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }
}
