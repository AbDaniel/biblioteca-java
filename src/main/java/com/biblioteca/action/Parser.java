package com.biblioteca.action;

import com.biblioteca.enums.MenuItem;
import com.biblioteca.model.User;
import com.biblioteca.repository.Library;
import com.biblioteca.view.ListView;
import com.biblioteca.visitor.BookVisitor;
import com.biblioteca.visitor.MovieVisitor;

import java.util.ArrayList;
import java.util.Map;

import static com.biblioteca.enums.MenuItem.*;
import static com.biblioteca.model.Book.REGULAR_BOOK_FORMAT;
import static com.biblioteca.model.Movie.REGULAR_MOVIE_FORMAT;

public class Parser {

    Library library;

    public Parser(Library library) {
        this.library = library;
    }

    public Action getAction(Map.Entry<MenuItem, String> userChoice, User user) {
        MenuItem selectedMenuItem = userChoice.getKey();
        switch (selectedMenuItem) {
            case LIST_BOOKS:
                return new ListLibrary(library, (ListView) LIST_BOOKS.view(),
                        new BookVisitor(new ArrayList<>(), REGULAR_BOOK_FORMAT));
            case LIST_MOVIES:
                return new ListLibrary(library, (ListView) LIST_MOVIES.view(),
                        new MovieVisitor(new ArrayList<>(), REGULAR_MOVIE_FORMAT));
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