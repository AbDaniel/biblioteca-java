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
public class AvailableBookTest {

    AvailableBook book;

    @Mock
    User user;

    @Mock
    Visitor visitor;

    @Before
    public void setUp() throws Exception {
        book = new AvailableBook("Lord of the Rings", "JR Toliken", 1930);
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

}