package com.biblioteca.action;

import com.biblioteca.model.User;
import com.biblioteca.view.View;
import com.biblioteca.constants.Constants;
import com.biblioteca.repository.Library;

public class Return implements Action {

    private Library library;
    private View view;
    private User user;

    public Return(Library library, View view, User user) {
        this.library = library;
        this.view = view;
        this.user = user;
    }

    @Override
    public void execute() {
        view.displayMessage(Constants.CHECKOUT_PROMPT_TEXT);
        String bookName = view.getString();
        if (library.returnItem(bookName, user)) {
            view.displayMessage(Constants.SUCCESSFUL_RETURN_TEXT);
        } else {
            view.displayMessage(Constants.BOOK_NOT_VALID_TEXT);
        }
    }

}
