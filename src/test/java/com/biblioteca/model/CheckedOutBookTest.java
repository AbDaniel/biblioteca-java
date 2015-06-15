package com.biblioteca.model;

import com.biblioteca.search.Searcher;
import com.biblioteca.visitor.Visitor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CheckedOutBookTest {

    private CheckedOutBook book;

    @Mock
    private User user;

    @Mock
    private Visitor visitor;

    @Mock
    private Searcher searcher;

    @Before
    public void setUp() throws Exception {
        book = new CheckedOutBook("Lord of the Rings", "JR Toliken", 1930);
    }

    @Test
    public void shouldReturnAnAvailableBookOnCheckout() {
        Book actualBook = book.returnBorrowable(user);

        assertTrue(actualBook instanceof AvailableBook);
    }

    @Test
    public void shouldReturnNullOnReturn() {
        Book actualBook = book.checkoutBorrowable(user);

        assertNull(actualBook);
    }

    @Test
    public void shouldAcceptVisitor() {
        book.accept(visitor);

        verify(visitor).visit(book);
    }

    @Test
    public void shouldAcceptSearcherWithRightSearchString() {
        when(searcher.getSearchString()).thenReturn("Lord of the Rings");

        book.match(searcher);

        verify(searcher).visit(book);
    }

}