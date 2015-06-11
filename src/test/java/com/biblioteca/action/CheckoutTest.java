package com.biblioteca.action;

import com.biblioteca.console.View;
import com.biblioteca.constants.Constants;
import com.biblioteca.model.Owner;
import com.biblioteca.repository.Borrowables;
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
    private Borrowables borrowables;

    @Mock
    private Owner owner;

    private Checkout command;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        command = new Checkout(borrowables, view);
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

        verify(borrowables).checkout(eq(bookName), Matchers.any(Owner.class));
    }

    @Test
    public void shouldNotifyIfBookIsNotValid() {
        String bookName = "123";
        when(view.getString()).thenReturn(bookName);
        when(borrowables.checkout(bookName, owner)).thenReturn(false);

        command.execute(owner);

        verify(view).displayMessage(Constants.BOOK_NOT_PRESENT_TEXT);
    }

    @Test
    public void shouldNotifyUserOnSuccessfulCheckout() {
        String bookName = "Lord of the Rings";
        when(view.getString()).thenReturn(bookName);
        when(borrowables.checkout(eq(bookName), Matchers.any(Owner.class))).thenReturn(true);

        command.execute(owner);

        verify(view).displayMessage(Constants.SUCCESSFUL_CHECKOUT_TEXT);
    }

}