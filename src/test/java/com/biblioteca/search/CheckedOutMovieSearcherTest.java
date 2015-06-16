package com.biblioteca.search;

import com.biblioteca.model.AvailableMovie;
import com.biblioteca.model.CheckedoutMovie;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CheckedOutMovieSearcherTest {

    CheckedOutMovieSearcher searcher;

    @Mock
    private List<CheckedoutMovie> movies;

    @Before
    public void setUp() {
        searcher = new CheckedOutMovieSearcher(movies, "The Matrix");
    }

    @Test
    public void shouldAddTheVisitedObjectToBooks() {
        CheckedoutMovie movie = new CheckedoutMovie("The Matrix", "The Wachowskis", 1999, 10);

        searcher.visit(movie);

        verify(movies).add(movie);
    }

    @Test
    public void shouldFindTheBookMatchingTheSearchString() {
        CheckedoutMovie movie = new CheckedoutMovie("The Matrix", "The Wachowskis", 1999, 10);

        searcher.visit(movie);
        boolean isEmpty = searcher.searchResults().isEmpty();

        assertFalse(isEmpty);
    }

    @Test
    public void shouldVerifyEqualsContract() {
        EqualsVerifier.forClass(CheckedOutMovieSearcher.class).usingGetClass().verify();
    }

}