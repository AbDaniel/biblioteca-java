package com.biblioteca.action;

import com.biblioteca.enums.MenuItem;
import com.biblioteca.model.User;
import com.biblioteca.repository.Library;
import com.biblioteca.view.ListView;
import com.biblioteca.visitor.BookVisitor;
import com.biblioteca.visitor.MovieVisitor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

import static com.biblioteca.enums.MenuItem.*;
import static com.biblioteca.model.Book.REGULAR_BOOK_FORMAT;
import static com.biblioteca.model.Movie.REGULAR_MOVIE_FORMAT;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class ParserTest {

    Parser parser;

    @Mock
    Library library;

    @Mock
    User user;

    @Before
    public void setUp() throws Exception {
        parser = new Parser(library);
    }

    @Test
    public void shouldReturnListBookActionWhenUserSelectsListBook() {
        MenuItem item = LIST_BOOKS;

        Action actualAction = parser.getAction(item, user);
        ListLibrary expected = new ListLibrary(library, (ListView) item.view(),
                new BookVisitor(new ArrayList<>(), REGULAR_BOOK_FORMAT));

        assertEquals(expected, actualAction);
    }

    @Test
    public void shouldReturnListMoviesActionWhenUserSelectsListBook() {
        MenuItem item = LIST_MOVIES;

        Action actualAction = parser.getAction(item, user);
        ListLibrary expected = new ListLibrary(library, (ListView) item.view(),
                new MovieVisitor(new ArrayList<>(), REGULAR_MOVIE_FORMAT));

        assertEquals(expected, actualAction);
    }

    @Test
    public void shouldReturnCheckoutActionActionWhenUserSelectsCheckoutBook() {
        MenuItem item = CHECKOUT_BOOK;

        Action actualAction = parser.getAction(item, user);
        Checkout expected = new Checkout(library, item.view(), user);

        assertEquals(expected, actualAction);
    }

    @Test
    public void shouldReturnCheckoutActionActionWhenUserSelectsCheckoutMovies() {
        MenuItem item = CHECKOUT_MOVIE;

        Action actualAction = parser.getAction(item, user);
        Checkout expected = new Checkout(library, item.view(), user);

        assertEquals(expected, actualAction);
    }

    @Test
    public void shouldReturnReturnActionActionWhenUserSelectsReturnBook() {
        MenuItem item = RETURN_BOOK;

        Action actualAction = parser.getAction(item, user);
        Return expected = new Return(library, item.view(), user);

        assertEquals(expected, actualAction);
    }


    @Test
    public void shouldReturnReturnActionActionWhenUserSelectsReturnMovies() {
        MenuItem item = RETURN_MOVIE;

        Action actualAction = parser.getAction(item, user);
        Return expected = new Return(library, item.view(), user);

        assertEquals(expected, actualAction);
    }


    @Test
    public void shouldReturnNullIfSelectedMenuItemIsNotCheckoutReturnOrList() {
        MenuItem item = QUIT;

        Action actualAction = parser.getAction(item, user);

        assertNull(actualAction);
    }

}