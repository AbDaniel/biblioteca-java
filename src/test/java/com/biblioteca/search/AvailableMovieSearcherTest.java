package com.biblioteca.search;

import com.biblioteca.model.AvailableBook;
import com.biblioteca.model.AvailableMovie;
import com.biblioteca.model.Book;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static junit.framework.Assert.assertFalse;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AvailableMovieSearcherTest {

    AvailableMovieSearcher searcher;

    @Mock
    private List<AvailableMovie> movies;

    @Before
    public void setUp() {
        searcher = new AvailableMovieSearcher(movies, "The Matrix");
    }

    @Test
    public void shouldAddTheVisitedObjectToBooks() {
        AvailableMovie movie = new AvailableMovie("The Matrix", "The Wachowskis", 1999, 10);

        searcher.visit(movie);

        verify(movies).add(movie);
    }

    @Test
    public void shouldFindTheBookMatchingTheSearchString() {
        AvailableMovie movie = new AvailableMovie("The Matrix", "The Wachowskis", 1999, 10);

        searcher.visit(movie);
        boolean isEmpty = searcher.searchResults().isEmpty();

        assertFalse(isEmpty);
    }

    @Test
    public void shouldVerifyEqualsContract() {
        EqualsVerifier.forClass(AvailableMovieSearcher.class).usingGetClass().verify();
    }

}