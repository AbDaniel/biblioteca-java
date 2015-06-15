package com.biblioteca.model;

import com.biblioteca.visitor.Visitor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CheckedOutBookTest {

    private CheckedOutBook book;

    @Mock
    private User user;

    @Mock
    private Visitor visitor;

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

}