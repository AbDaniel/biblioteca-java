package com.biblioteca.action;

import com.biblioteca.enums.MenuItem;
import com.biblioteca.model.Book;
import com.biblioteca.model.User;
import com.biblioteca.repository.Library;
import com.biblioteca.search.AvailableBookSearcher;
import com.biblioteca.view.ListView;
import com.biblioteca.visitor.AvailableBookVisitor;

import java.util.ArrayList;
import java.util.Map;

import static com.biblioteca.enums.MenuItem.*;

public class Parser {

    Library bookLibrary;
    private Library movieLibrary;

    public Parser(Library bookLibrary, Library movieLibrary) {
        this.bookLibrary = bookLibrary;
        this.movieLibrary = movieLibrary;
    }

    public Action getAction(Map.Entry<MenuItem, String> userChoice, User user) {
        MenuItem selectedMenuItem = userChoice.getKey();
        switch (selectedMenuItem) {
            case LIST_BOOKS:
                return new ListLibrary(bookLibrary, (ListView) LIST_BOOKS.view(), new AvailableBookVisitor(new ArrayList<>(), Book.REGULAR_BOOK_FORMAT));
            case LIST_MOVIES:
                return new ListLibrary(movieLibrary, (ListView) LIST_MOVIES.view(), new AvailableBookVisitor(new ArrayList<>(), Book.REGULAR_BOOK_FORMAT));
            case CHECKOUT_BOOK:
                return new Checkout(bookLibrary, user, new AvailableBookSearcher(new
                        ArrayList<>(), userChoice.getValue()));
            case CHECKOUT_MOVIE:
                return new Checkout(movieLibrary, user, new AvailableBookSearcher(new
                        ArrayList<>(), userChoice.getValue()));
            case RETURN_BOOK:
                return new Return(bookLibrary, user, userChoice.getValue());
            case RETURN_MOVIE:
                return new Return(movieLibrary, user, userChoice.getValue());
            default:
                return null;
        }
    }

}