package com.biblioteca.action;

import com.biblioteca.enums.MenuItem;
import com.biblioteca.model.Owner;
import com.biblioteca.repository.Library;
import com.biblioteca.view.ListView;

import static com.biblioteca.enums.MenuItem.*;

public class Parser {

    Library library;

    public Parser(Library library) {
        this.library = library;
    }

    public Action getAction(MenuItem selectedMenuItem, Owner owner) {
        switch (selectedMenuItem) {
            case LIST_BOOKS:
                return new ListLibrary(library, (ListView) LIST_BOOKS.getView());
            case CHECKOUT_BOOK:
                return new Checkout(library, LIST_BOOKS.getView());
            case RETURN_BOOK:
                return new Return(library, LIST_BOOKS.getView());
            default:
                return null;
        }
    }

}
