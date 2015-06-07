package com.biblioteca.command;

import com.biblioteca.console.BibliotecaConsoleIO;
import com.biblioteca.repository.Books;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static com.biblioteca.console.BibliotecaConsoleIO.*;
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

    @Test
    public void shouldCallCheckoutWithGivenInput() {
        String bookName = "Lord of the Rings";
        when(bibliotecaConsoleIO.getBookNameFromUser()).thenReturn(bookName);

        command.execute();

        Mockito.verify(books).checkout(bookName);
    }

    @Test
    public void shouldNotifyIfBookIsNotValid() {
        String bookName = "123";
        when(bibliotecaConsoleIO.getBookNameFromUser()).thenReturn(bookName);
        when(books.checkout(bookName)).thenReturn(false);

        command.execute();

        Mockito.verify(bibliotecaConsoleIO).displayMessage(BOOK_NOT_PRESENT_TEXT);
    }

    @Test
    public void shouldNotifyIfBookSearchedIsPresent() {
        String bookName = "Lord of the Rings";
        when(bibliotecaConsoleIO.getBookNameFromUser()).thenReturn(bookName);
        when(books.checkout(bookName)).thenReturn(true);

        command.execute();

        Mockito.verify(bibliotecaConsoleIO).displayMessage(SUCCESSFULL_CHECKOUT_TEXT);
    }

}