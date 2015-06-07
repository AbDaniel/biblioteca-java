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
    public void shouldFindGivenBookInBooks() {
        String bookName = "Lord of the Rings";
        when(bibliotecaConsoleIO.getBookNameFromUser()).thenReturn(bookName);

        command.execute();

        Mockito.verify(books).findByName(bookName);
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

    @Test
    public void shouldNotifyIfBookSearchedIsNotPresent() {
        String bookName = "123";
        when(bibliotecaConsoleIO.getBookNameFromUser()).thenReturn(bookName);
        when(books.findByName(bookName)).thenReturn(null);

        command.execute();

        Mockito.verify(bibliotecaConsoleIO).displayMessage(BOOK_NOT_PRESENT_TEXT);
    }

    @Test
    public void shouldNotifyIfBookSearchedIsPresent() {
        String bookName = "Lord of the Rings";
        when(bibliotecaConsoleIO.getBookNameFromUser()).thenReturn(bookName);
        when(books.findByName(bookName)).thenReturn(new Book("Lord of the Rings", null, 0));

        command.execute();

        Mockito.verify(bibliotecaConsoleIO).displayMessage(BOOK_PRESENT_TEXT);
    }

    @Test
    public void shouldCallMoveToCheckoutOnBooksIfBookIsValid() {
        String bookName = "Lord of the Rings";
        Book book = new Book("Lord of the Rings", null, 0);
        when(bibliotecaConsoleIO.getBookNameFromUser()).thenReturn(bookName);
        when(books.findByName(bookName)).thenReturn(book);

        command.execute();

        Mockito.verify(books).moveToCheckout(book);
    }


    @Test
    public void shouldNotCallMoveToCheckoutOnBooksIfBookIsInValid() {
        String bookName = "Autobiography";
        Book book = new Book(bookName, "Daniel", 2025);
        when(bibliotecaConsoleIO.getBookNameFromUser()).thenReturn(bookName);
        when(books.findByName(bookName)).thenReturn(null);

        command.execute();

        Mockito.verify(books, never()).moveToCheckout(book);
    }

}