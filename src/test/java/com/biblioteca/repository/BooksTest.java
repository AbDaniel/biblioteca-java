package com.biblioteca.repository;

import com.biblioteca.model.Book;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.when;

public class BooksTest {

    Books books;

    @Before
    public void setUp() {
        List<Book> availableBooks = new ArrayList<>();
        availableBooks.add(new Book("Lord of the Rings", "JR Toliken", 1930));
        availableBooks.add(new Book("Harry Potter", "JK Rowling", 1992));
        availableBooks.add(new Book("Catch-22", "Joesph Heller", 1950));
        availableBooks.add(new Book("Winds of Winter", "George RR Martin", 2017));
        List<Book> checkedOutBooks = new ArrayList<>();
        checkedOutBooks.add(new Book("Death in the Nile", "Agatha Christie", 1925));
        checkedOutBooks.add(new Book("1984", "George Orwell", 1950));
        books = new Books(availableBooks, checkedOutBooks);
    }

    @Test
    public void shouldReturnNullIfBookIsNotAvailable() {
        String bookName = "The Fellowship Of the Ring";

        Book actualBook = books.findByName(bookName);

        assertNull(actualBook);
    }

    @Test
    public void shouldFindGivenBookByName() {
        String bookName = "Lord of the Rings";
        String author = "JRR Tolkien";
        int year = 1930;

        Book actualBook = books.findByName(bookName);
        Book expectedBook = new Book(bookName, author, year);

        assertEquals(actualBook, expectedBook);
    }

    @Test
    public void shouldRemoveBookFromAvailableListDuringCheckout() {
        Book book = new Book("Lord of the Rings", null, 0);
        List availableBooks = mock(List.class);
        List checkedOutBooks = mock(List.class);
        books = new Books(availableBooks, checkedOutBooks);

        books.moveToCheckout(book);

        Mockito.verify(availableBooks).remove(book);
    }

    @Test
    public void shouldAddBookToCheckoutListDuringCheckout() {
        Book book = new Book("Lord of the Rings", null, 0);
        List availableBooks = mock(List.class);
        List checkedOutBooks = mock(List.class);
        when(availableBooks.remove(book)).thenReturn(true);
        books = new Books(availableBooks, checkedOutBooks);

        books.moveToCheckout(book);

        Mockito.verify(checkedOutBooks).add(book);
    }

    @Test
    public void shouldReturnFalseIfBookGivenWasNotPresentInAvailableList() {
        Book book = new Book("Autobiography", "Daniel", 2025);

        assertFalse(books.moveToCheckout(book));
    }

    @Test
    public void shouldNotAddToCheckoutListIfBookWasNotRemovedInAvailableList() {
        Book book = new Book("Autobiography", "Daniel", 2025);
        List availableBooks = mock(List.class);
        List checkedOutBooks = mock(List.class);
        books = new Books(availableBooks, checkedOutBooks);

        books.moveToCheckout(book);

        Mockito.verify(checkedOutBooks, never()).add(book);
    }

    @Test
    public void shouldReturnTrueIfBookWasCheckedOut() {
        Book book = new Book("Lord of the Rings", null, 0);

        assertTrue(books.moveToCheckout(book));
    }

    @Test
    public void shouldRemoveBookFromCheckoutListDuringReturn() {
        Book book = new Book("Lord of the Rings", null, 0);
        List availableBooks = mock(List.class);
        List checkedOutBooks = mock(List.class);
        books = new Books(availableBooks, checkedOutBooks);

        books.moveToAvailable(book);

        Mockito.verify(checkedOutBooks).remove(book);
    }

    @Test
    public void shouldAddBookToAvailableListDuringReturn() {
        Book book = new Book("Lord of the Rings", null, 0);
        List availableBooks = mock(List.class);
        List checkedOutBooks = mock(List.class);
        when(availableBooks.remove(book)).thenReturn(true);
        books = new Books(availableBooks, checkedOutBooks);

        books.moveToAvailable(book);

        Mockito.verify(availableBooks).add(book);
    }

    @Test
    public void shouldReturnFalseIfBookGivenWasNotPresentInCheckoutList() {
        Book book = new Book("Autobiography", "Daniel", 2025);

        assertFalse(books.moveToAvailable(book));
    }

    @Test
    public void shouldNotAddToAvailableListIfBookWasNotRemovedInCheckoutList() {
        Book book = new Book("Autobiography", "Daniel", 2025);
        List availableBooks = mock(List.class);
        List checkedOutBooks = mock(List.class);
        books = new Books(availableBooks, checkedOutBooks);

        books.moveToAvailable(book);

        Mockito.verify(availableBooks, never()).add(book);
    }

}