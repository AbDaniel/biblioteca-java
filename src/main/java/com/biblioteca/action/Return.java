package com.biblioteca.action;

import com.biblioteca.view.View;
import com.biblioteca.constants.Constants;
import com.biblioteca.model.Owner;
import com.biblioteca.repository.Borrowables;

public class Return implements Action {

    private Borrowables borrowables;
    private View view;

    public Return(Borrowables borrowables, View view) {
        this.borrowables = borrowables;
        this.view = view;
    }

    @Override
    public void execute(Owner owner) {
        view.displayMessage(Constants.CHECKOUT_PROMPT_TEXT);
        String bookName = view.getString();
        if (borrowables.returnItem(bookName, owner)) {
            view.displayMessage(Constants.SUCCESSFUL_RETURN_TEXT);
        } else {
            view.displayMessage(Constants.BOOK_NOT_VALID_TEXT);
        }
    }

}
