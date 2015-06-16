package com.biblioteca.model;

import com.biblioteca.listener.Listener;
import com.biblioteca.search.Searcher;
import com.biblioteca.visitor.Visitor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.biblioteca.constants.Constants.SUCCESS_MOVIE_RETURN;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CheckedoutMovieTest {

    private CheckedoutMovie movie;

    @Mock
    private User user;

    @Mock
    private Visitor visitor;

    @Mock
    private Searcher searcher;

    @Mock
    private Listener listener;

    @Before
    public void setUp() throws Exception {
        movie = new CheckedoutMovie("The Matrix", "The Wachowskis", 1999, 10);
        movie.addListener(listener);
    }

    @Test
    public void shouldReturnAnAvailableBookOnCheckout() {
        Movie actualBook = movie.returnBorrowable(user);

        assertTrue(actualBook instanceof AvailableMovie);
    }

    @Test
    public void shouldReturnNullOnReturn() {
        Movie actualBook = movie.checkoutBorrowable(user);

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
    public void shouldUpdateListenerOnSuccessfulReturn() {
        movie.returnBorrowable(user);

        verify(listener).update(SUCCESS_MOVIE_RETURN);
    }

    @Test
    public void shouldRemoveBorrowableFromUserDuringCheckout() {
        movie.returnBorrowable(user);

        verify(user).removeBorrowable(movie);
    }

}