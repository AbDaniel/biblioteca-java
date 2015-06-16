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
    public void shouldReturnStringByFormat() {
        String formattedString = movie.toString(Movie.REGULAR_MOVIE_FORMAT);

        String expectedString = "name='The Matrix', director='The Wachowskis', year=1999, rating=10";

        assertEquals(expectedString, formattedString);
    }

}