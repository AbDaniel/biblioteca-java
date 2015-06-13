package com.biblioteca.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class BookTest {

    Book book;

    @Mock
    User user;

    @Before
    public void setUp() {
        book = new Book("Lord of the Rings", "JR Toliken", 1930);
    }

    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(Book.class).usingGetClass().verify();
    }

    @Test
    public void shouldReturnFormatedStringToRespresentBookState() {
        String actualString = book.toString();
        String expectedString = "name='Lord of the Rings', author='JR Toliken', year=1930";

        assertEquals(expectedString, actualString);
    }

    @Test
    public void shouldReturnTrueIfCurrentCheckoutStateIsFalseDuringCheckout() {
        assertTrue(book.checkout(user));
    }

    @Test
    public void shouldReturnFalseIfCurrentCheckoutStateIsTrueDuringCheckout() {
        book.checkout(user);

        assertFalse(book.checkout(user));
    }

    @Test
    public void shouldReturnTrueIfCurrentCheckoutStateIsTrueDuringReturn() {
        book.checkout(user);

        assertTrue(book.returnItem(user));
    }

    @Test
    public void shouldReturnFalseIfCurrentCheckoutStateIsFalseDuringReturn() {
        assertFalse(book.returnItem(user));
    }

    @Test
    public void shouldReturnFalseIfBookNameIsNotValid() {
        assertFalse(book.isEqualTo("aasd"));
    }

    @Test
    public void shouldReturnTrueIfBookNameIsValid() {
        assertTrue(book.isEqualTo("Lord of the Rings"));
    }

    @Test
    public void shouldAddBookToUser() {
        book.checkout(user);

        verify(user).addBorrowable(book);
    }

}