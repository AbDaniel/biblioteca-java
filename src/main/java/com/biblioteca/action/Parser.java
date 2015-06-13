package com.biblioteca.action;

import com.biblioteca.enums.MenuItem;
import com.biblioteca.model.User;
import com.biblioteca.repository.Library;
import com.biblioteca.view.ListView;
import com.biblioteca.visitor.BookVisitor;

import static com.biblioteca.enums.MenuItem.LIST_BOOKS;
import static com.biblioteca.model.Book.REGULAR_BOOK_FORMAT;

public class Parser {

    Library library;

    public Parser(Library library) {
        this.library = library;
    }

    public Action getAction(MenuItem selectedMenuItem, User user) {
        switch (selectedMenuItem) {
            case LIST_BOOKS:
                return new ListLibrary(library, (ListView) LIST_BOOKS.view(), new BookVisitor(REGULAR_BOOK_FORMAT));
            case CHECKOUT_BOOK:
                return new Checkout(library, LIST_BOOKS.view(), user);
            case RETURN_BOOK:
                return new Return(library, LIST_BOOKS.view(), user);
            default:
                return null;
        }
    }

}
