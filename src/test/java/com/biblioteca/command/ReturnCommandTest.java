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
    public void shouldFindGivenBookInBooks() {
        String bookName = "Lord of the Rings";
        when(bibliotecaConsoleIO.getBookNameFromUser()).thenReturn(bookName);

        command.execute();

        Mockito.verify(books).findInCheckedOutBooks(bookName);
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
        when(books.findInCheckedOutBooks(bookName)).thenReturn(null);

        command.execute();

        Mockito.verify(bibliotecaConsoleIO).displayMessage(BOOK_NOT_VALID_TEXT);
    }

    @Test
    public void shouldNotifyIfBookSearchedIsPresent() {
        String bookName = "Lord of the Rings";
        when(bibliotecaConsoleIO.getBookNameFromUser()).thenReturn(bookName);
        when(books.findInCheckedOutBooks(bookName)).thenReturn(new Book("Lord of the Rings", null, 0));

        command.execute();

        Mockito.verify(bibliotecaConsoleIO).displayMessage(SUCCESSFULL_RETURN_TEXT);
    }

    @Test
    public void shouldCallMoveToAvailableOnBooksIfBookIsValid() {
        String bookName = "Lord of the Rings";
        Book book = new Book("Lord of the Rings", null, 0);
        when(bibliotecaConsoleIO.getBookNameFromUser()).thenReturn(bookName);
        when(books.findInCheckedOutBooks(bookName)).thenReturn(book);

        command.execute();

        Mockito.verify(books).returnBook(book);
    }

    @Test
    public void shouldNotCallMoveToAvailableOnBooksIfBookIsInValid() {
        String bookName = "Autobiography";
        Book book = new Book(bookName, "Daniel", 2025);
        when(bibliotecaConsoleIO.getBookNameFromUser()).thenReturn(bookName);

        command.execute();

        Mockito.verify(books, never()).returnBook(book);
    }

}