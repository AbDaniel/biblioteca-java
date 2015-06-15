package com.biblioteca.model;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.*;

public class CheckedOutBookTest {

    CheckedOutBook book;

    @Mock
    User user;

    @Before
    public void setUp() throws Exception {
        book = new CheckedOutBook("Lord of the Rings", "JR Toliken", 1930);
    }

    @Test
    public void shouldReturnAnAvailableBookOnCheckout() {
        Book actualBook = book.returnBorrowable(user);

        assertTrue(actualBook instanceof AvailableBook);
    }

}