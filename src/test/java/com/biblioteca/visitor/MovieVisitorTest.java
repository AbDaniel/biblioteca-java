package com.biblioteca.visitor;

import com.biblioteca.model.AvailableBook;
import com.biblioteca.model.AvailableMovie;
import com.biblioteca.model.Book;
import com.biblioteca.model.Movie;
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
public class MovieVisitorTest {

    MovieVisitor visitor;

    @Mock
    private List<Movie> movies;

    @Before
    public void setUp() throws Exception {
        List<Visitable> visitables = new ArrayList<>();
        visitables.add(new AvailableBook("Lord of the Rings", "JR Toliken", 1930));
        visitables.add(new AvailableBook("Harry Potter", "JK Rowling", 1992));
        visitables.add(new AvailableBook("Catch-22", "Joesph Heller", 1950));
        visitables.add(new AvailableBook("Winds of Winter", "George RR Martin", 2017));
        visitables.add(new AvailableMovie("The Matrix", "The Wachowskis", 1999, 10));
        visitor = new MovieVisitor(new ArrayList<>(), REGULAR_MOVIE_FORMAT);
        visitables.forEach(visitable -> visitable.accept(visitor));
    }

    @Test
    public void shouldReturnMovieAsString() {
        String actualString = visitor.visitables();

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
        visitor = new MovieVisitor(movies, REGULAR_MOVIE_FORMAT);
        Movie movie = new AvailableMovie("The Matrix", "The Wachowskis", 1999, 10);

        visitor.visit(movie);

        verify(movies).add(movie);
    }

}