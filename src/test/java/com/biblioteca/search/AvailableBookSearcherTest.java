package com.biblioteca.search;

import com.biblioteca.listener.Listener;
import com.biblioteca.model.AvailableBook;
import com.biblioteca.model.Book;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static com.biblioteca.constants.Constants.BOOK_NOT_PRESENT_TEXT;
import static junit.framework.Assert.assertFalse;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AvailableBookSearcherTest {

    private AvailableBookSearcher searcher;

    @Mock
    private List<AvailableBook> books;

    @Mock
    private Listener listener;

    @Before
    public void setUp() {
        searcher = new AvailableBookSearcher(books, "Winds of Winter");
    }

    @Test
    public void shouldAddTheVisitedObjectToBooks() {
        AvailableBook book = new AvailableBook("Winds of Winter", "George RR Martin", 2017);

        searcher.visit(book);

        verify(books).add(book);
    }

    @Test
    public void shouldFindTheBookMatchingTheSearchString() {
        Book book = new AvailableBook("Winds of Winter", "George RR Martin", 2017);

        searcher.visit(book);
        boolean isEmpty = searcher.searchResults().isEmpty();

        assertFalse(isEmpty);
    }

    @Test
    public void shouldUpdateLisitenerIfSearchResultsIsEmpty() {
        searcher = new AvailableBookSearcher(new ArrayList<>(), "Winds of Winter");
        searcher.addListener(listener);

        searcher.searchResults();

        verify(listener).update(BOOK_NOT_PRESENT_TEXT);
    }


    @Test
    public void shouldVerifyEqualsContract() {
        EqualsVerifier.forClass(AvailableBookSearcher.class).usingGetClass().verify();
    }
}