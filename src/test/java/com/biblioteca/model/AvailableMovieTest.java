package com.biblioteca.model;

import com.biblioteca.listener.Listener;
import com.biblioteca.search.Searcher;
import com.biblioteca.visitor.Visitor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.biblioteca.constants.Constants.SUCCESSFUL_BOOK_CHECKOUT;
import static com.biblioteca.constants.Constants.SUCCESS_MOVIE_CHECKOUT;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AvailableMovieTest {

    AvailableMovie movie;

    @Mock
    User user;

    @Mock
    Visitor visitor;

    @Mock
    Searcher searcher;

    @Mock
    private Listener listener;

    @Before
    public void setUp() throws Exception {
        movie = new AvailableMovie("The Matrix", "The Wachowskis", 1999, 10);
        movie.addListener(listener);
    }

    @Test
    public void shouldReturnAnAvailableBookOnCheckout() {
        Movie actualBook = movie.checkoutBorrowable(user);

        assertTrue(actualBook instanceof CheckedoutMovie);
    }

    @Test
    public void shouldReturnNullOnReturn() {
        Movie actualBook = movie.returnBorrowable(user);

        assertNull(actualBook);
    }

    @Test
    public void shouldAcceptVisitor() {
        movie.accept(visitor);

        verify(visitor).visit(movie);
    }

    @Test
    public void shouldAcceptSearcherWithRightSearchString() {
        when(searcher.getSearchString()).thenReturn("The Matrix");

        movie.match(searcher);

        verify(searcher).visit(movie);
    }

    @Test
    public void shouldUpdateListenerOnReturn() {
        movie.checkoutBorrowable(user);

        verify(listener).update(SUCCESS_MOVIE_CHECKOUT);
    }

    @Test
    public void shouldAddBorrowableToUser() {
        Movie actualMovie = movie.checkoutBorrowable(user);

        verify(user).addBorrowable(actualMovie);
    }

}