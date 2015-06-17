package com.biblioteca.search;

import com.biblioteca.listener.Listener;
import com.biblioteca.model.CheckedOutBook;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static com.biblioteca.constants.Constants.NOT_A_VALID_BOOK_RETURN;
import static junit.framework.Assert.assertFalse;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CheckedOutBookSearcherTest {

    private CheckedOutBookSearcher searcher;

    @Mock
    private List<CheckedOutBook> books;

    @Mock
    private Listener listener;

    @Before
    public void setUp() {
        searcher = new CheckedOutBookSearcher(books, "Winds of Winter");
    }

    @Test
    public void shouldAddTheVisitedObjectToBooks() {
        CheckedOutBook book = new CheckedOutBook("Winds of Winter", "George RR Martin", 2017);

        searcher.visit(book);

        verify(books).add(book);
    }

    @Test
    public void shouldFindTheBookMatchingTheSearchString() {
        CheckedOutBook book = new CheckedOutBook("Winds of Winter", "George RR Martin", 2017);

        searcher.visit(book);
        boolean isEmpty = searcher.searchResults().isEmpty();

        assertFalse(isEmpty);
    }

    @Test
    public void shouldUpdateLisitenerIfSearchResultsIsEmpty() {
        searcher = new CheckedOutBookSearcher(new ArrayList<>(), "Winds of Winter");
        searcher.addListener(listener);

        searcher.searchResults();

        verify(listener).update(NOT_A_VALID_BOOK_RETURN);
    }

    @Test
    public void shouldVerifyEqualsContract() {
        EqualsVerifier.forClass(CheckedOutBookSearcher.class).usingGetClass().verify();
    }

}