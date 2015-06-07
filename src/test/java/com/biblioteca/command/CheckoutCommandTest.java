package com.biblioteca.command;

import com.biblioteca.console.BibliotecaConsoleIO;
import com.biblioteca.dao.Books;
import com.biblioteca.model.Book;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static com.biblioteca.console.BibliotecaConsoleIO.*;
import static org.junit.Assert.*;

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

        Mockito.verify(bibliotecaConsoleIO).displayMessage(BOOK_NAME_TEXT);
    }

    @Test
    public void shouldGetBookNameFromUser() {
        command.execute();

        Mockito.verify(bibliotecaConsoleIO).getBookNameFromUser();
    }

}