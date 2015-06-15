package com.biblioteca.search;

import com.biblioteca.model.AvailableBook;
import com.biblioteca.model.Book;
import com.biblioteca.model.CheckedOutBook;
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
public class CheckedOutBookSearcherTest {

    CheckedOutBookSearcher searcher;

    @Mock
    private List<CheckedOutBook> books;

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

}