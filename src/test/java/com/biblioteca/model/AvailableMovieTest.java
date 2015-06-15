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
public class AvailableMovieTest {

    AvailableMovie movie;

    @Mock
    User user;

    @Mock
    Visitor visitor;

    @Before
    public void setUp() throws Exception {
        movie = new AvailableMovie("The Matrix", "The Wachowskis", 1999, 10);
    }

    @Test
    public void shouldReturnAnAvailableBookOnCheckout() {
        Movie actualBook = movie.checkoutBorrowable(user);

        assertTrue(actualBook instanceof CheckedoutMovie);
    }

    @Test
    public void shouldReturnNullOnReturn() {
        Movie actualBook = movie.returnBorrowable(user);

        assertNull(actualBook);
    }

    @Test
    public void shouldAcceptVisitor() {
        movie.accept(visitor);

        verify(visitor).visit(movie);
    }

}