package com.biblioteca.visitor;

import com.biblioteca.model.*;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static com.biblioteca.model.Movie.REGULAR_MOVIE_FORMAT;
import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AvailableMovieVisitorTest {

    AvailableMovieVisitor visitor;

    @Mock
    List<AvailableMovie> movies;

    @Before
    public void setUp() throws Exception {
        List<Visitable> visitables = new ArrayList<>();
        visitables.add(new AvailableBook("Lord of the Rings", "JR Toliken", 1930));
        visitables.add(new AvailableBook("Harry Potter", "JK Rowling", 1992));
        visitables.add(new AvailableBook("Catch-22", "Joesph Heller", 1950));
        visitables.add(new AvailableBook("Winds of Winter", "George RR Martin", 2017));
        visitables.add(new AvailableMovie("The Matrix", "The Wachowskis", 1999, 10));
        visitables.add(new CheckedOutBook("Dance with Dragons", "George RR Martin", 2017));
        visitor = new AvailableMovieVisitor(new ArrayList<>(), REGULAR_MOVIE_FORMAT);
        visitables.forEach(visitable -> visitable.accept(visitor));
    }

    @Test
    public void shouldReturnBookAsString() {
        String actualString = visitor.visitablesAsString();

        String expectedString = "name='The Matrix', director='The Wachowskis', year=1999, rating=10\n";

        assertEquals(expectedString, actualString);
    }

    @Test
    public void shouldReturnSizeOfVisitablesVisited() {
        int actualSize = visitor.size();

        assertEquals(1, actualSize);
    }

    @Test
    public void shouldAddTheVisitedObjectToBooks() {
        visitor = new AvailableMovieVisitor(movies, REGULAR_MOVIE_FORMAT);
        AvailableMovie movie = new AvailableMovie("The Matrix", "The Wachowskis", 1999, 10);

        visitor.visit(movie);

        verify(movies).add(movie);
    }

    @Test
    public void shouldVerifyEqualsContract() {
        EqualsVerifier.forClass(AvailableMovieVisitor.class).usingGetClass().verify();
    }
}