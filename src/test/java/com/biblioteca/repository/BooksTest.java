package com.biblioteca.repository;

import com.biblioteca.model.Book;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class BooksTest {

    Books books;

    @Before
    public void setUp() {
        List<Book> books = new ArrayList<>();
        books.add(new Book("Lord of the Rings", "JR Toliken", 1930));
        books.add(new Book("Harry Potter", "JK Rowling", 1992));
        books.add(new Book("Catch-22", "Joesph Heller", 1950));
        books.add(new Book("Winds of Winter", "George RR Martin", 2017));
        Book book = new Book("1984", "George Orwell", 1950);
        book.checkout();
        books.add(book);
        book = new Book("Alchemist", "Paulo Coelho", 1988);
        book.checkout();
        books.add(book);
        this.books = new Books(books);
    }

    @Test
    public void shouldReturnListOfAvailableBooksOfRightSize() {
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
        String bookName = "1984";

        assertFalse(books.checkout(bookName));
    }

}