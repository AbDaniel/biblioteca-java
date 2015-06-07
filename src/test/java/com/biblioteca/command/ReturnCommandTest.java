package com.biblioteca.command;

import com.biblioteca.console.BibliotecaConsoleIO;
import com.biblioteca.repository.Books;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static com.biblioteca.console.BibliotecaConsoleIO.CHECKOUT_PROMPT_TEXT;
import static org.mockito.Mockito.when;

public class ReturnCommandTest {

    @Mock
    BibliotecaConsoleIO bibliotecaConsoleIO;

    @Mock
    private Books books;

    private ReturnCommand command;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        command = new ReturnCommand(books, bibliotecaConsoleIO);
    }

    @Test
    public void shouldDisplayMessageAskingUserToInputBookName() {
        command.execute();

        Mockito.verify(bibliotecaConsoleIO).displayMessage(CHECKOUT_PROMPT_TEXT);
    }

    @Test
    public void shouldFindGivenBookInBooks() {
        String bookName = "Lord of the Rings";
        when(bibliotecaConsoleIO.getBookNameFromUser()).thenReturn(bookName);

        command.execute();

        Mockito.verify(books).findInCheckedOutBooks(bookName);
    }

}