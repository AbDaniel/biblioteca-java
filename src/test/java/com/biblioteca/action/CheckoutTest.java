package com.biblioteca.action;

import com.biblioteca.model.User;
import com.biblioteca.view.View;
import com.biblioteca.constants.Constants;
import com.biblioteca.repository.Library;
import nl.jqno.equalsverifier.EqualsVerifier;
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
    private User user;

    private Checkout command;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        command = new Checkout(library, view, user);
    }

    @Test
    public void shouldDisplayMessageAskingUserToInputBookName() {
        command.execute();

        verify(view).displayMessage(Constants.CHECKOUT_PROMPT_TEXT);
    }

    @Test
    public void shouldGetBookNameFromUser() {
        command.execute();

        verify(view).getString();
    }

    @Test
    public void shouldCallCheckoutWithGivenInput() {
        String bookName = "Lord of the Rings";
        when(view.getString()).thenReturn(bookName);

        command.execute();

        verify(library).checkout(eq(bookName), Matchers.any(User.class));
    }

    @Test
    public void shouldNotifyIfBookIsNotValid() {
        String bookName = "123";
        when(view.getString()).thenReturn(bookName);
        when(library.checkout(bookName, user)).thenReturn(false);

        command.execute();

        verify(view).displayMessage(Constants.BOOK_NOT_PRESENT_TEXT);
    }

    @Test
    public void shouldNotifyUserOnSuccessfulCheckout() {
        String bookName = "Lord of the Rings";
        when(view.getString()).thenReturn(bookName);
        when(library.checkout(eq(bookName), Matchers.any(User.class))).thenReturn(true);

        command.execute();

        verify(view).displayMessage(Constants.SUCCESSFUL_CHECKOUT_TEXT);
    }

    @Test
    public void shouldVerifyEqualsContract() {
        EqualsVerifier.forClass(Checkout.class).usingGetClass();
    }

}