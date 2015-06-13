package com.biblioteca.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MovieTest {

    Movie movie;

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
}