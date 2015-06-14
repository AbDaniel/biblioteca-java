package com.biblioteca.model;

import com.biblioteca.visitor.Visitor;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MovieTest {

    Movie movie;

    @Mock
    Visitor visitor;

    @Mock
    User user;

    @Before
    public void setUp() throws Exception {
        movie = new Movie("The Matrix", "The Wachowskis", 1999, 10);
    }

    @Test
    public void shouldVerifyEqualsContract() {
        EqualsVerifier.forClass(Movie.class).usingGetClass();
    }

    @Test
    public void shouldReturnFormatedStringToRespresentBookState() {
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

}