package com.biblioteca.search;

import com.biblioteca.listener.Listener;
import com.biblioteca.model.AvailableMovie;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static com.biblioteca.constants.Constants.MOVIE_IS_NOT_PRESENT;
import static junit.framework.Assert.assertFalse;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AvailableMovieSearcherTest {

    AvailableMovieSearcher searcher;

    @Mock
    private List<AvailableMovie> movies;

    @Mock
    private Listener listener;

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
    public void shouldUpdateLisitenerIfSearchResultsIsEmpty() {
        searcher = new AvailableMovieSearcher(new ArrayList<>(), "Winds of Winter");
        searcher.addListener(listener);

        searcher.searchResults();

        verify(listener).update(MOVIE_IS_NOT_PRESENT);
    }


    @Test
    public void shouldVerifyEqualsContract() {
        EqualsVerifier.forClass(AvailableMovieSearcher.class).usingGetClass().verify();
    }

}