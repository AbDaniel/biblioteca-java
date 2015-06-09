package com.biblioteca.command;

import com.biblioteca.console.BibliotecaConsoleIO;
import com.biblioteca.model.Owner;
import com.biblioteca.repository.Borrowables;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static com.biblioteca.console.BibliotecaConsoleIO.*;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

public class CheckoutCommandTest {

    @Mock
    BibliotecaConsoleIO bibliotecaConsoleIO;

    @Mock
    private Borrowables borrowables;

    @Mock
    private Owner owner;

    private CheckoutCommand command;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        command = new CheckoutCommand(borrowables, bibliotecaConsoleIO);
    }

    @Test
    public void shouldDisplayMessageAskingUserToInputBookName() {
        command.execute();

        Mockito.verify(bibliotecaConsoleIO).displayMessage(CHECKOUT_PROMPT_TEXT);
    }

    @Test
    public void shouldGetBookNameFromUser() {
        command.execute();

        Mockito.verify(bibliotecaConsoleIO).getBookNameFromUser();
    }

    @Test
    public void shouldCallCheckoutWithGivenInput() {
        String bookName = "Lord of the Rings";
        when(bibliotecaConsoleIO.getBookNameFromUser()).thenReturn(bookName);

        command.execute();

        Mockito.verify(borrowables).checkout(eq(bookName), Matchers.any(Owner.class));
    }

    @Test
    public void shouldNotifyIfBookIsNotValid() {
        String bookName = "123";
        when(bibliotecaConsoleIO.getBookNameFromUser()).thenReturn(bookName);
        when(borrowables.checkout(bookName, owner)).thenReturn(false);

        command.execute();

        Mockito.verify(bibliotecaConsoleIO).displayMessage(BOOK_NOT_PRESENT_TEXT);
    }

    @Test
    public void shouldNotifyUserOnSuccessfulCheckout() {
        String bookName = "Lord of the Rings";
        when(bibliotecaConsoleIO.getBookNameFromUser()).thenReturn(bookName);
        when(borrowables.checkout(eq(bookName), Matchers.any(Owner.class))).thenReturn(true);

        command.execute();

        Mockito.verify(bibliotecaConsoleIO).displayMessage(SUCCESSFULL_CHECKOUT_TEXT);
    }

}