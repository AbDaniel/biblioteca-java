package com.biblioteca.repository;

import com.biblioteca.model.Book;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class BooksTest {

    Books books;

    @Mock
    List<Book> bookList;

    @Before
    public void setUp() {
        initMocks(this);
        books = new Books(bookList);
    }

    void setUpWithData() {
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book("Lord of the Rings", "JR Toliken", 1930));
        bookList.add(new Book("Harry Potter", "JK Rowling", 1992));
        bookList.add(new Book("Catch-22", "Joesph Heller", 1950));
        bookList.add(new Book("Winds of Winter", "George RR Martin", 2017));
        Book book = new Book("1984", "George Orwell", 1950);
        book.checkout();
        bookList.add(book);
        book = new Book("Alchemist", "Paulo Coelho", 1988);
        book.checkout();
        bookList.add(book);
        this.books = new Books(bookList);
    }

    @Test
    public void shouldReturnListOfAvailableBooksOfRightSize() {
        setUpWithData();
        int actualSize = books.allAvailableBooks().size();

        assertEquals(4, actualSize);
    }

    @Test
    public void shouldReturnZeroSizedListIfNoAvailableBooks() {
        this.books = new Books(null);
        int actualSize = books.allAvailableBooks().size();

        assertEquals(0, actualSize);
    }

    @Test
    public void shouldReturnFalseIfBookIsNotAvailableDuringCheckout() {
        setUpWithData();
        String bookName = "1984";

        assertFalse(books.checkout(bookName));
    }

    @Test
    public void shouldFindTheBookWithGivenNameDuringCheckout() {
        String name = "Lord of the Rings";
        Book book = new Book(name, null, 0);
        when(bookList.get(Matchers.any(Integer.class))).thenReturn(new Book("My Own Book", null, 0));

        books.checkout(name);

        Mockito.verify(bookList).indexOf(book);
    }

    @Test
    public void shouldReturnTrueOnSuccessfulCheckOut() {
        setUpWithData();
        String name = "Lord of the Rings";

        assertTrue(books.checkout(name));
    }

}