package com.biblioteca.model;

import com.biblioteca.constants.Constants;
import com.biblioteca.listener.Listener;
import com.biblioteca.visitor.BookVisitor;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.biblioteca.constants.Constants.BOOK_NOT_PRESENT_TEXT;
import static com.biblioteca.constants.Constants.SUCCESSFUL_CHECKOUT_TEXT;
import static com.biblioteca.model.Book.REGULAR_BOOK_FORMAT;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class BookTest {

    private Book book;

    @Mock
    private User user;

    @Mock
    private BookVisitor visitor;

    @Mock
    Listener listener;

    @Before
    public void setUp() {
        book = new Book("Lord of the Rings", "JR Toliken", 1930);
        book.addListener(listener);
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
    public void shouldAddBookToUserOnCheckout() {
        book.checkout(user);

        verify(user).addBorrowable(book);
    }

    @Test
    public void shouldRemoveBookFromUserOnReturn() {
        book.checkout(user);

        book.returnItem(user);

        verify(user).removeOwnable(book);
    }

    @Test
    public void shouldAcceptVisitor() {
        book.accept(visitor);

        verify(visitor).visit(book);
    }

    @Test
    public void shouldNotifyListenerOnFailureOfCheckOut() {
        book.checkout(user);
        book.checkout(user);

        verify(listener).update(BOOK_NOT_PRESENT_TEXT);
    }

    @Test
    public void shouldNotifyListenerOnSuccessOfCheckOut() {
        book.checkout(user);

        verify(listener).update(SUCCESSFUL_CHECKOUT_TEXT);
    }

    @Test
    public void shouldReturnStringByFormat() {
        String formattedString = book.toString(REGULAR_BOOK_FORMAT);

        String expectedString = "name='Lord of the Rings', author='JR Toliken', year=1930";

        assertEquals(expectedString, formattedString);
    }

}