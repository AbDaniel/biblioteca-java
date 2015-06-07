package com.biblioteca.command;

import com.biblioteca.console.BibliotecaConsoleIO;
import com.biblioteca.repository.Books;
import com.biblioteca.model.Book;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static com.biblioteca.console.BibliotecaConsoleIO.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.when;

public class CheckoutCommandTest {

    @Mock
    BibliotecaConsoleIO bibliotecaConsoleIO;

    @Mock
    private Books books;

    private CheckoutCommand command;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        command = new CheckoutCommand(books, bibliotecaConsoleIO);
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

}