package com.biblioteca.search;

import com.biblioteca.model.AvailableBook;
import com.biblioteca.model.Book;
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
public class AvailableBookSearcherTest {

    AvailableBookSearcher searcher;

    @Mock
    private List<AvailableBook> books;

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
}