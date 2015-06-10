package com.biblioteca.command;

import com.biblioteca.console.BibliotecaConsoleIO;
import com.biblioteca.constants.Constants;
import com.biblioteca.model.Owner;
import com.biblioteca.repository.Borrowables;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

public class ReturnCommandTest {

    @Mock
    BibliotecaConsoleIO bibliotecaConsoleIO;

    @Mock
    private Borrowables borrowables;

    @Mock
    private Owner owner;

    private ReturnCommand command;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        command = new ReturnCommand(borrowables, bibliotecaConsoleIO, owner);
    }

    @Test
    public void shouldDisplayMessageAskingUserToInputBookName() {
        command.execute();

        Mockito.verify(bibliotecaConsoleIO).displayMessage(Constants.CHECKOUT_PROMPT_TEXT);
    }

    @Test
    public void shouldGetBookNameFromUser() {
        command.execute();

        Mockito.verify(bibliotecaConsoleIO).getBookNameFromUser();
    }

    @Test
    public void shouldCallReturnBookWithGivenInput() {
        String bookName = "Lord of the Rings";
        when(bibliotecaConsoleIO.getBookNameFromUser()).thenReturn(bookName);

        command.execute();

        Mockito.verify(borrowables).returnItem(eq(bookName), Matchers.any(Owner.class));
    }

    @Test
    public void shouldNotifyIfUserInputsInvalidBookName() {
        String bookName = "123";
        when(bibliotecaConsoleIO.getBookNameFromUser()).thenReturn(bookName);
        when(borrowables.returnItem(eq(bookName), Matchers.any(Owner.class))).thenReturn(false);

        command.execute();

        Mockito.verify(bibliotecaConsoleIO).displayMessage(Constants.BOOK_NOT_VALID_TEXT);
    }

    @Test
    public void shouldNotifyUserOnSuccessfulReturn() {
        String bookName = "Lord of the Rings";
        when(bibliotecaConsoleIO.getBookNameFromUser()).thenReturn(bookName);
        when(borrowables.returnItem(eq(bookName), Matchers.any(Owner.class))).thenReturn(true);

        command.execute();

        Mockito.verify(bibliotecaConsoleIO).displayMessage(Constants.SUCCESSFUL_RETURN_TEXT);
    }

}