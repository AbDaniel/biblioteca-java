package com.biblioteca.model;

import com.biblioteca.listener.Listener;
import com.biblioteca.visitor.Visitor;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MovieTest {

    Movie movie;

    @Mock
    Visitor visitor;

    @Mock
    User user;

    @Mock
    Listener listener;

    @Before
    public void setUp() throws Exception {
        movie = new Movie("The Matrix", "The Wachowskis", 1999, 10);
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

}