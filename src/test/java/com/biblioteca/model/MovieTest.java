package com.biblioteca.model;

import com.biblioteca.listener.Listener;
import com.biblioteca.search.Searcher;
import com.biblioteca.visitor.Visitor;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.biblioteca.constants.Constants.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MovieTest {

    Movie movie;

    @Mock
    Visitor visitor;

    @Mock
    User user;

    @Mock
    Listener listener;

    @Mock
    Searcher searcher;

    @Before
    public void setUp() throws Exception {
        movie = new AvailableMovie("The Matrix", "The Wachowskis", 1999, 10);
        movie.addListener(listener);
    }

    @Test
    public void shouldVerifyEqualsContract() {
        EqualsVerifier.forClass(Movie.class).usingGetClass().verify();
    }

    @Test
    public void shouldReturnFormatedStringToRespresentMovieState() {
        String actualString = movie.toString();
        String expectedString = "name='The Matrix', director='The Wachowskis', year=1999, rating=10";

        assertEquals(expectedString, actualString);
    }

    @Test
    public void shouldAcceptVisitor() {
        movie.accept(visitor);

        verify(visitor).visit(movie);
    }

    @Test
    public void shouldReturnStringByFormat() {
        String formattedString = movie.toString(Movie.REGULAR_MOVIE_FORMAT);

        String expectedString = "name='The Matrix', director='The Wachowskis', year=1999, rating=10";

        assertEquals(expectedString, formattedString);
    }

    @Test
    public void shouldReturnFalseIfCurrentCheckoutStateIsTrueDuringCheckout() {
        movie.checkout(user);

        assertFalse(movie.checkout(user));
    }

    @Test
    public void shouldReturnTrueIfCurrentCheckoutStateIsFalseDuringCheckout() {
        assertTrue(movie.checkout(user));
    }

    @Test
    public void shouldReturnTrueIfCurrentCheckoutStateIsTrueDuringReturn() {
        movie.checkout(user);

        assertTrue(movie.returnItem(user));
    }

    @Test
    public void shouldReturnFalseIfCurrentCheckoutStateIsFalseDuringReturn() {
        assertFalse(movie.returnItem(user));
    }

    @Test
    public void shouldReturnFalseIfMovieNameIsNotValid() {
        assertFalse(movie.isEqualTo("aasd"));
    }

    @Test
    public void shouldReturnTrueIfMovieNameIsValid() {
        assertTrue(movie.isEqualTo("The Matrix"));
    }

    @Test
    public void shouldAddMovieToUserOnCheckout() {
        movie.checkout(user);

        verify(user).addBorrowable(movie);
    }

    @Test
    public void shouldRemoveMovieFromUserOnReturn() {
        movie.checkout(user);

        movie.returnItem(user);

        verify(user).removeOwnable(movie);
    }


    @Test
    public void shouldNotifyListenerOnFailureOfCheckOut() {
        movie.checkout(user);
        movie.checkout(user);

        verify(listener).update(MOVIE_IS_NOT_PRESENT);
    }

    @Test
    public void shouldNotifyListenerOnSuccessOfCheckOut() {
        movie.checkout(user);

        verify(listener).update(SUCCESS_MOVIE_CHECKOUT);
    }


    @Test
    public void shouldNotifyListenerOnFailureOfReturn() {
        movie.returnItem(user);

        verify(listener).update(MOVIE_IS_NOT_VALID);
    }


    @Test
    public void shouldNotifyListenerOnSuccessOfReturn() {
        movie.checkout(user);
        movie.returnItem(user);

        verify(listener).update(SUCCESS_MOVIE_RETURN);
    }

    @Test
    public void shouldAcceptSearcherWithRightSearchString() {
        when(searcher.getSearchString()).thenReturn("The Matrix");

        movie.match(searcher);

        verify(searcher).visit(movie);
    }

}