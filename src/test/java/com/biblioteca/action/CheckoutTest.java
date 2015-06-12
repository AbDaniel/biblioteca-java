package com.biblioteca.action;

import com.biblioteca.view.View;
import com.biblioteca.constants.Constants;
import com.biblioteca.model.Owner;
import com.biblioteca.repository.Library;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class CheckoutTest {

    @Mock
    View view;

    @Mock
    private Library library;

    @Mock
    private Owner owner;

    private Checkout command;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        command = new Checkout(library, view);
    }

    @Test
    public void shouldDisplayMessageAskingUserToInputBookName() {
        command.execute(owner);

        verify(view).displayMessage(Constants.CHECKOUT_PROMPT_TEXT);
    }

    @Test
    public void shouldGetBookNameFromUser() {
        command.execute(owner);

        verify(view).getString();
    }

    @Test
    public void shouldCallCheckoutWithGivenInput() {
        String bookName = "Lord of the Rings";
        when(view.getString()).thenReturn(bookName);

        command.execute(owner);

        verify(library).checkout(eq(bookName), Matchers.any(Owner.class));
    }

    @Test
    public void shouldNotifyIfBookIsNotValid() {
        String bookName = "123";
        when(view.getString()).thenReturn(bookName);
        when(library.checkout(bookName, owner)).thenReturn(false);

        command.execute(owner);

        verify(view).displayMessage(Constants.BOOK_NOT_PRESENT_TEXT);
    }

    @Test
    public void shouldNotifyUserOnSuccessfulCheckout() {
        String bookName = "Lord of the Rings";
        when(view.getString()).thenReturn(bookName);
        when(library.checkout(eq(bookName), Matchers.any(Owner.class))).thenReturn(true);

        command.execute(owner);

        verify(view).displayMessage(Constants.SUCCESSFUL_CHECKOUT_TEXT);
    }

}