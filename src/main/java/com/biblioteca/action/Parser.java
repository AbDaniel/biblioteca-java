package com.biblioteca.action;

import com.biblioteca.enums.MenuItem;
import com.biblioteca.model.User;
import com.biblioteca.repository.Library;
import com.biblioteca.view.ListView;

import java.util.Map;

import static com.biblioteca.enums.MenuItem.*;

public class Parser {

    Library library;

    public Parser(Library library) {
        this.library = library;
    }

    public Action getAction(Map.Entry<MenuItem, String> userChoice, User user) {
        MenuItem selectedMenuItem = userChoice.getKey();
        switch (selectedMenuItem) {
            case LIST_BOOKS:
                return new ListLibrary(library, (ListView) LIST_BOOKS.view());
            case LIST_MOVIES:
                return new ListLibrary(library, (ListView) LIST_MOVIES.view());
            case CHECKOUT_BOOK:
            case CHECKOUT_MOVIE:
                return new Checkout(library, user, userChoice.getValue());
            case RETURN_BOOK:
            case RETURN_MOVIE:
                return new Return(library, RETURN_BOOK.view(), user);
            default:
                return null;
        }
    }

}