package com.biblioteca.search;

import com.biblioteca.model.AvailableBook;
import com.biblioteca.model.Book;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class BookSearcherTest {

    BookSearcher searcher;

    @Mock
    private List<Book> books;

    @Before
    public void setUp() {
        searcher = new BookSearcher(books, "Winds of Winter");
    }

    @Test
    public void shouldAddTheVisitedObjectToBooks() {
        Book book = new AvailableBook("Winds of Winter", "George RR Martin", 2017);

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