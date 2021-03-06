package com.biblioteca.parser;

import com.biblioteca.action.*;
import com.biblioteca.enums.LibrarianMenuItem;
import com.biblioteca.enums.MenuItem;
import com.biblioteca.model.User;
import com.biblioteca.repository.Accounts;
import com.biblioteca.repository.Library;
import com.biblioteca.search.AvailableBookSearcher;
import com.biblioteca.search.AvailableMovieSearcher;
import com.biblioteca.search.CheckedOutBookSearcher;
import com.biblioteca.search.CheckedOutMovieSearcher;
import com.biblioteca.view.ListView;
import com.biblioteca.visitor.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.biblioteca.enums.MenuItem.LIST_BOOKS;
import static com.biblioteca.enums.MenuItem.LIST_MOVIES;
import static com.biblioteca.model.Book.REGULAR_BOOK_FORMAT;
import static com.biblioteca.model.Movie.REGULAR_MOVIE_FORMAT;

public class Parser {

    private Library borrowableLibrary;
    private Accounts accounts;

    public Parser(Library borrowableLibrary, Accounts accounts) {
        this.borrowableLibrary = borrowableLibrary;
        this.accounts = accounts;
    }

    public Action getAction(Map.Entry<MenuItem, String> userChoice, User user) {
        MenuItem selectedMenuItem = userChoice.getKey();
        switch (selectedMenuItem) {
            case LIST_BOOKS:
                return new ListLibrary(borrowableLibrary, (ListView) LIST_BOOKS.view(), new AvailableBookVisitor(
                        new ArrayList<>(), REGULAR_BOOK_FORMAT));
            case LIST_MOVIES:
                return new ListLibrary(borrowableLibrary, (ListView) LIST_MOVIES.view(), new AvailableMovieVisitor(
                        new ArrayList<>(), REGULAR_MOVIE_FORMAT));
            case CHECKOUT_BOOK:
                return new Checkout(borrowableLibrary, user, new AvailableBookSearcher(new
                        ArrayList<>(), userChoice.getValue()));
            case CHECKOUT_MOVIE:
                return new Checkout(borrowableLibrary, user, new AvailableMovieSearcher(new
                        ArrayList<>(), userChoice.getValue()));
            case RETURN_BOOK:
                return new Return(borrowableLibrary, user, new CheckedOutBookSearcher(new
                        ArrayList<>(), userChoice.getValue()));
            case RETURN_MOVIE:
                return new Return(borrowableLibrary, user, new CheckedOutMovieSearcher(new
                        ArrayList<>(), userChoice.getValue()));
            case INVALID:
                return new Invalid();
            case QUIT:
                return new Quit();
            case LOGOUT:
                return new Logout();
            default:
                return null;
        }
    }

    public Action getLibrarianAction(LibrarianMenuItem menuItem) {
        switch (menuItem) {
            case LIST_BOOK_DEFAULTERS:
                return new ListDefaulters(accounts, new BorrowableDefaulterVisitor(new CheckedoutBookVisitor(new
                        ArrayList<>(), REGULAR_BOOK_FORMAT), new HashMap<>()));
            case LIST_MOVIE_DEFAULTERS:
                return new ListDefaulters(accounts, new BorrowableDefaulterVisitor(new CheckedoutMovieVisitor(new
                        ArrayList<>(), REGULAR_MOVIE_FORMAT), new HashMap<>()));
            case INVALID:
                return new Invalid();
            case QUIT:
                return new Quit();
            case LOGOUT:
                return new Logout();
            default:
                return null;
        }
    }

}