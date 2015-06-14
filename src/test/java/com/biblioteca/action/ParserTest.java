package com.biblioteca.action;

import com.biblioteca.enums.MenuItem;
import com.biblioteca.model.User;
import com.biblioteca.repository.Library;
import com.biblioteca.view.ListView;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.AbstractMap.SimpleEntry;

import static com.biblioteca.enums.MenuItem.*;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class ParserTest {

    Parser parser;

    @Mock
    Library library;

    @Mock
    User user;
    private String bookName;

    private SimpleEntry<MenuItem, String> userChoice;

    @Before
    public void setUp() throws Exception {
        parser = new Parser(library);
    }

    @Test
    public void shouldReturnListBookActionWhenUserSelectsListBook() {
        userChoice = new SimpleEntry<>(LIST_BOOKS, null);
        ListLibrary expected = new ListLibrary(library, (ListView) userChoice.getKey().view()
        );

        Action actualAction = parser.getAction(userChoice, user);

        bookName = "Lord of the Rings";
        assertEquals(expected, actualAction);
    }

    @Test
    public void shouldReturnListMoviesActionWhenUserSelectsListBook() {
        MenuItem item = LIST_MOVIES;
        userChoice = new SimpleEntry<>(item, null);
        ListLibrary expected = new ListLibrary(library, (ListView) item.view()
        );

        Action actualAction = parser.getAction(userChoice, user);

        assertEquals(expected, actualAction);
    }

    @Test
    public void shouldReturnCheckoutActionActionWhenUserSelectsCheckoutBook() {
        MenuItem item = CHECKOUT_BOOK;
        userChoice = new SimpleEntry<>(item, null);
        Checkout expected = new Checkout(library, user, bookName);

        Action actualAction = parser.getAction(userChoice, user);

        assertEquals(expected, actualAction);
    }

    @Test
    public void shouldReturnCheckoutActionActionWhenUserSelectsCheckoutMovies() {
        MenuItem item = CHECKOUT_MOVIE;
        userChoice = new SimpleEntry<>(item, null);

        Action actualAction = parser.getAction(userChoice, user);
        Checkout expected = new Checkout(library, user, bookName);

        assertEquals(expected, actualAction);
    }

    @Test
    public void shouldReturnReturnActionActionWhenUserSelectsReturnBook() {
        MenuItem item = RETURN_BOOK;
        userChoice = new SimpleEntry<>(item, null);
        Return expected = new Return(library, item.view(), user);

        Action actualAction = parser.getAction(userChoice, user);

        assertEquals(expected, actualAction);
    }


    @Test
    public void shouldReturnReturnActionActionWhenUserSelectsReturnMovies() {
        MenuItem item = RETURN_MOVIE;
        userChoice = new SimpleEntry<>(item, null);
        Return expected = new Return(library, item.view(), user);

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

}