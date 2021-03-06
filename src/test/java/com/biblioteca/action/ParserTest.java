package com.biblioteca.action;

import com.biblioteca.enums.LibrarianMenuItem;
import com.biblioteca.enums.MenuItem;
import com.biblioteca.model.User;
import com.biblioteca.parser.Parser;
import com.biblioteca.repository.Accounts;
import com.biblioteca.repository.Library;
import com.biblioteca.search.*;
import com.biblioteca.view.ListView;
import com.biblioteca.visitor.*;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.HashMap;

import static com.biblioteca.enums.LibrarianMenuItem.LIST_BOOK_DEFAULTERS;
import static com.biblioteca.enums.LibrarianMenuItem.LIST_MOVIE_DEFAULTERS;
import static com.biblioteca.enums.MenuItem.*;
import static com.biblioteca.model.Book.REGULAR_BOOK_FORMAT;
import static com.biblioteca.model.Movie.REGULAR_MOVIE_FORMAT;
import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ParserTest {

    private Parser parser;

    @Mock
    private Library borrowableLibrary;

    @Mock
    private User user;

    @Mock
    private Searcher searcher;

    @Mock
    private Accounts accounts;

    @Mock
    private CheckedoutBookVisitor checkedoutBookVisitor;

    private BorrowableDefaulterVisitor borrowableDefaulterVisitor;

    private String bookName;

    private SimpleEntry<MenuItem, String> userChoice;

    @Before
    public void setUp() throws Exception {
        parser = new Parser(borrowableLibrary, accounts);
    }

    @Test
    public void shouldReturnListBookActionWhenUserSelectsListBook() {
        userChoice = new SimpleEntry<>(LIST_BOOKS, null);
        ListLibrary expected = new ListLibrary(borrowableLibrary, (ListView) userChoice.getKey().view(),
                new AvailableBookVisitor(new ArrayList<>(), REGULAR_BOOK_FORMAT));

        Action actualAction = parser.getAction(userChoice, user);

        bookName = "Lord of the Rings";
        assertEquals(expected, actualAction);
    }

    @Test
    public void shouldReturnListMoviesActionWhenUserSelectsListBook() {
        MenuItem item = LIST_MOVIES;
        userChoice = new SimpleEntry<>(item, null);
        ListLibrary expected = new ListLibrary(borrowableLibrary, (ListView) item.view(),
                new AvailableMovieVisitor(new ArrayList<>(), REGULAR_MOVIE_FORMAT));

        Action actualAction = parser.getAction(userChoice, user);

        assertEquals(expected, actualAction);
    }

    @Test
    public void shouldReturnCheckoutActionActionWhenUserSelectsCheckoutBook() {
        MenuItem item = CHECKOUT_BOOK;
        userChoice = new SimpleEntry<>(item, "Lord of the Rings");
        Searcher searcher = new AvailableBookSearcher(new ArrayList<>(), "Lord of the Rings");
        Checkout expected = new Checkout(borrowableLibrary, user, searcher);

        Action actualAction = parser.getAction(userChoice, user);

        assertEquals(expected, actualAction);
    }

    @Test
    public void shouldReturnCheckoutActionActionWhenUserSelectsCheckoutMovies() {
        MenuItem item = CHECKOUT_MOVIE;
        userChoice = new SimpleEntry<>(item, "Lord of the Rings");
        Searcher searcher = new AvailableMovieSearcher(new ArrayList<>(), "Lord of the Rings");
        Checkout expected = new Checkout(borrowableLibrary, user, searcher);

        Action actualAction = parser.getAction(userChoice, user);

        assertEquals(expected, actualAction);
    }

    @Test
    public void shouldReturnReturnActionActionWhenUserSelectsReturnBook() {
        MenuItem item = RETURN_BOOK;
        userChoice = new SimpleEntry<>(item, "Lord of the Rings");
        Searcher searcher = new CheckedOutBookSearcher(new ArrayList<>(), "Lord of the Rings");
        Return expected = new Return(borrowableLibrary, user, searcher);

        Action actualAction = parser.getAction(userChoice, user);

        assertEquals(expected, actualAction);
    }

    @Test
    public void shouldReturnReturnActionActionWhenUserSelectsReturnMovies() {
        MenuItem item = RETURN_MOVIE;
        userChoice = new SimpleEntry<>(item, "Lord of the Rings");
        Searcher searcher = new CheckedOutMovieSearcher(new ArrayList<>(), "Lord of the Rings");
        Return expected = new Return(borrowableLibrary, user, searcher);

        Action actualAction = parser.getAction(userChoice, user);

        assertEquals(expected, actualAction);
    }

    @Test
    public void shouldReturnQuitIfSelectedMenuItemIsNotCheckoutReturnOrList() {
        MenuItem item = MenuItem.QUIT;
        userChoice = new SimpleEntry<>(item, null);
        Quit expected = new Quit();

        Action actualAction = parser.getAction(userChoice, user);

        assertEquals(expected, actualAction);
    }

    @Test
    public void shouldReturnInvalidActionIfSelectedMenuItemIsNotCheckoutReturnOrList() {
        MenuItem item = MenuItem.INVALID;
        userChoice = new SimpleEntry<>(item, null);

        Action actualAction = parser.getAction(userChoice, user);

        assertTrue(actualAction instanceof Invalid);
    }

    @Test
    public void shouldReturnLogoutActionIfSelectedMenuItemIsNotCheckoutReturnOrList() {
        MenuItem item = MenuItem.LOGOUT;
        userChoice = new SimpleEntry<>(item, null);

        Action actualAction = parser.getAction(userChoice, user);

        assertTrue(actualAction instanceof Logout);
    }


    @Test
    public void shouldReturnListBookDefaulterActionIfLibrarianSelectsListDefaulters() {
        checkedoutBookVisitor = new CheckedoutBookVisitor(new ArrayList<>(), REGULAR_BOOK_FORMAT);
        borrowableDefaulterVisitor = new BorrowableDefaulterVisitor(checkedoutBookVisitor, new HashMap<>());
        LibrarianMenuItem item = LIST_BOOK_DEFAULTERS;
        Action expected = new ListDefaulters(accounts, borrowableDefaulterVisitor);

        Action actualAction = parser.getLibrarianAction(item);

        assertEquals(expected, actualAction);
    }

    @Test
    public void shouldReturnListMovieDefaulterActionIfLibrarianSelectsListDefaulters() {
        CheckedoutMovieVisitor checkedoutMovieVisitor = new CheckedoutMovieVisitor(new ArrayList<>(), REGULAR_MOVIE_FORMAT);
        borrowableDefaulterVisitor = new BorrowableDefaulterVisitor(checkedoutMovieVisitor, new HashMap<>());
        LibrarianMenuItem item = LIST_MOVIE_DEFAULTERS;
        Action expected = new ListDefaulters(accounts, borrowableDefaulterVisitor);

        Action actualAction = parser.getLibrarianAction(item);

        assertEquals(expected, actualAction);
    }

    @Test
    public void shouldReturnInvalidActionIfLibrarianSelectsInvalidInput() {
        LibrarianMenuItem item = LibrarianMenuItem.INVALID;

        Action actualAction = parser.getLibrarianAction(item);

        TestCase.assertTrue(actualAction instanceof Invalid);
    }


    @Test
    public void shouldReturnQuitActionIfLibrarianSelectsQuit() {
        LibrarianMenuItem item = LibrarianMenuItem.QUIT;

        Action actualAction = parser.getLibrarianAction(item);

        TestCase.assertTrue(actualAction instanceof Quit);
    }


    @Test
    public void shouldReturnLogoutActionIfLibrarianSelectsLogout() {
        LibrarianMenuItem item = LibrarianMenuItem.LOGOUT;

        Action actualAction = parser.getLibrarianAction(item);

        TestCase.assertTrue(actualAction instanceof Logout);
    }

}