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
        view.displayMessage(Constants.ENTER_BOOK_NAME);
        String bookName = view.getString();
        if (library.returnItem(bookName, user)) {
            view.displayMessage(Constants.SUCCESSFUL_RETURN_TEXT);
        } else {
            view.displayMessage(Constants.BOOK_NOT_VALID_TEXT);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Return aReturn = (Return) o;

        if (library != null ? !library.equals(aReturn.library) : aReturn.library != null) return false;
        return !(user != null ? !user.equals(aReturn.user) : aReturn.user != null);

    }

    @Override
    public int hashCode() {
        int result = library != null ? library.hashCode() : 0;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }
}
