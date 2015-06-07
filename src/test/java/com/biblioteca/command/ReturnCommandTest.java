package com.biblioteca.command;

import com.biblioteca.console.BibliotecaConsoleIO;
import com.biblioteca.model.Book;
import com.biblioteca.repository.Books;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static com.biblioteca.console.BibliotecaConsoleIO.*;
import static org.mockito.Mockito.never;
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
    public void shouldGetBookNameFromUser() {
        command.execute();

        Mockito.verify(bibliotecaConsoleIO).getBookNameFromUser();
    }

    @Test
    public void shouldCallReturnBookWithGivenInput() {
        String bookName = "Lord of the Rings";
        when(bibliotecaConsoleIO.getBookNameFromUser()).thenReturn(bookName);

        command.execute();

        Mockito.verify(books).returnBook(bookName);
    }

    @Test
    public void shouldNotifyIfUserInputsInvalidBookName() {
        String bookName = "123";
        when(bibliotecaConsoleIO.getBookNameFromUser()).thenReturn(bookName);
        when(books.returnBook(bookName)).thenReturn(false);

        command.execute();

        Mockito.verify(bibliotecaConsoleIO).displayMessage(BOOK_NOT_VALID_TEXT);
    }

    @Test
    public void shouldNotifyUserOnSuccessfulReturn() {
        String bookName = "Lord of the Rings";
        when(bibliotecaConsoleIO.getBookNameFromUser()).thenReturn(bookName);
        when(books.returnBook(bookName)).thenReturn(true);

        command.execute();

        Mockito.verify(bibliotecaConsoleIO).displayMessage(SUCCESSFULL_RETURN_TEXT);
    }

}