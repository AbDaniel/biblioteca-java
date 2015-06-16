package com.biblioteca.model;

import com.biblioteca.constants.Constants;
import com.biblioteca.listener.Listener;
import com.biblioteca.search.AvailableBookSearcher;
import com.biblioteca.search.BookSearcher;
import com.biblioteca.search.Searchable;
import com.biblioteca.search.Searcher;
import com.biblioteca.visitor.Visitor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.biblioteca.constants.Constants.SUCCESSFUL_BOOK_CHECKOUT;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AvailableBookTest {

    AvailableBook book;

    @Mock
    User user;

    @Mock
    Visitor visitor;

    @Mock
    AvailableBookSearcher searcher;

    @Mock
    private Listener listener;

    @Before
    public void setUp() throws Exception {
        book = new AvailableBook("Lord of the Rings", "JR Toliken", 1930);
        book.addListener(listener);
    }

    @Test
    public void shouldReturnAnAvailableBookOnCheckout() {
        Book actualBook = book.checkoutBorrowable(user);

        assertTrue(actualBook instanceof CheckedOutBook);
    }

    @Test
    public void shouldReturnNullOnReturn() {
        Book actualBook = book.returnBorrowable(user);

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

    @Test
    public void shouldUpdateListenerOnReturn() {
        Book actualBook = book.checkoutBorrowable(user);

        verify(listener).update(SUCCESSFUL_BOOK_CHECKOUT);
    }

    @Test
    public void shouldAddBorrowableToUser() {
        Book actualBook = book.checkoutBorrowable(user);

        verify(user).addBorrowable(actualBook);
    }

}