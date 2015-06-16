package com.biblioteca.action;

import com.biblioteca.enums.LibrarianMenuItem;
import com.biblioteca.enums.MenuItem;
import com.biblioteca.model.Book;
import com.biblioteca.model.User;
import com.biblioteca.repository.Accounts;
import com.biblioteca.repository.Library;
import com.biblioteca.search.*;
import com.biblioteca.view.ListView;
import com.biblioteca.visitor.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.HashMap;

import static com.biblioteca.enums.LibrarianMenuItem.LIST_BOOK_DEFAULTERS;
import static com.biblioteca.enums.MenuItem.*;
import static com.biblioteca.model.Movie.REGULAR_MOVIE_FORMAT;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class ParserTest {

    Parser parser;

    @Mock
    Library bookLibrary;

    @Mock
    Library movieLibray;

    @Mock
    User user;

    @Mock
    Searcher searcher;

    @Mock
    Accounts accounts;

    BorrowableDefaulterVisitor borrowableDefaulterVisitor;

    @Mock
    CheckedoutBookVisitor checkedoutBookVisitor;

    private String bookName;

    private SimpleEntry<MenuItem, String> userChoice;

    @Before
    public void setUp() throws Exception {
        parser = new Parser(bookLibrary, movieLibray, accounts);
    }

    @Test
    public void shouldReturnListBookActionWhenUserSelectsListBook() {
        userChoice = new SimpleEntry<>(LIST_BOOKS, null);
        ListLibrary expected = new ListLibrary(bookLibrary, (ListView) userChoice.getKey().view(),
                new AvailableBookVisitor(new ArrayList<>(), Book.REGULAR_BOOK_FORMAT));

        Action actualAction = parser.getAction(userChoice, user);

        bookName = "Lord of the Rings";
        assertEquals(expected, actualAction);
    }

    @Test
    public void shouldReturnListMoviesActionWhenUserSelectsListBook() {
        MenuItem item = LIST_MOVIES;
        userChoice = new SimpleEntry<>(item, null);
        ListLibrary expected = new ListLibrary(movieLibray, (ListView) item.view(),
                new AvailableMovieVisitor(new ArrayList<>(), REGULAR_MOVIE_FORMAT));

        Action actualAction = parser.getAction(userChoice, user);

        assertEquals(expected, actualAction);
    }

    @Test
    public void shouldReturnCheckoutActionActionWhenUserSelectsCheckoutBook() {
        MenuItem item = CHECKOUT_BOOK;
        userChoice = new SimpleEntry<>(item, "Lord of the Rings");
        Searcher searcher = new AvailableBookSearcher(new ArrayList<>(), "Lord of the Rings");
        Checkout expected = new Checkout(bookLibrary, user, searcher);

        Action actualAction = parser.getAction(userChoice, user);

        assertEquals(expected, actualAction);
    }

    @Test
    public void shouldReturnCheckoutActionActionWhenUserSelectsCheckoutMovies() {
        MenuItem item = CHECKOUT_MOVIE;
        userChoice = new SimpleEntry<>(item, "Lord of the Rings");
        Searcher searcher = new AvailableMovieSearcher(new ArrayList<>(), "Lord of the Rings");
        Checkout expected = new Checkout(movieLibray, user, searcher);

        Action actualAction = parser.getAction(userChoice, user);

        assertEquals(expected, actualAction);
    }

    @Test
    public void shouldReturnReturnActionActionWhenUserSelectsReturnBook() {
        MenuItem item = RETURN_BOOK;
        userChoice = new SimpleEntry<>(item, "Lord of the Rings");
        Searcher searcher = new CheckedOutBookSearcher(new ArrayList<>(), "Lord of the Rings");
        Return expected = new Return(bookLibrary, user, searcher);

        Action actualAction = parser.getAction(userChoice, user);

        assertEquals(expected, actualAction);
    }

    @Test
    public void shouldReturnReturnActionActionWhenUserSelectsReturnMovies() {
        MenuItem item = RETURN_MOVIE;
        userChoice = new SimpleEntry<>(item, "Lord of the Rings");
        Searcher searcher = new CheckedOutMovieSearcher(new ArrayList<>(), "Lord of the Rings");
        Return expected = new Return(movieLibray, user, searcher);

        Action actualAction = parser.getAction(userChoice, user);

        assertEquals(expected, actualAction);
    }

    @Test
    public void shouldReturnNullIfSelectedMenuItemIsNotCheckoutReturnOrList() {
        MenuItem item = QUIT;
        userChoice = new SimpleEntry<>(item, null);

        Action actualAction = parser.getAction(userChoice, user);

        assertNull(actualAction);
    }


    @Test
    public void shouldReturnListBookDefaulterActionIfLibrarianSelectsListDefaulters() {
        borrowableDefaulterVisitor = new BorrowableDefaulterVisitor(checkedoutBookVisitor, new HashMap<>());
        LibrarianMenuItem item = LIST_BOOK_DEFAULTERS;
        Action expected = new ListDefaulters(accounts, borrowableDefaulterVisitor);

        Action actualAction = parser.getLibrarianAction(item);

        assertEquals(expected, actualAction);
    }
}