package com.biblioteca.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class BookTest {

    Book book;

    @Mock
    Owner owner;

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
        assertTrue(book.checkout(owner));
    }

    @Test
    public void shouldReturnFalseIfCurrentCheckoutStateIsTrueDuringCheckout() {
        book.checkout(owner);

        assertFalse(book.checkout(owner));
    }

    @Test
    public void shouldReturnTrueIfCurrentCheckoutStateIsTrueDuringReturn() {
        book.checkout(owner);

        assertTrue(book.returnItem(owner));
    }

    @Test
    public void shouldReturnFalseIfCurrentCheckoutStateIsFalseDuringReturn() {
        assertFalse(book.returnItem(owner));
    }

    @Test
    public void shouldReturnFalseIfBookNameIsNotValid() {
        assertFalse(book.isEqualTo("aasd"));
    }

    @Test
    public void shouldReturnTrueIfBookNameIsValid() {
        assertTrue(book.isEqualTo("Lord of the Rings"));
    }

}