package com.biblioteca.dao;

import com.biblioteca.model.Book;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class BooksTest {

    Books books;

    @Before
    public void setUp() {
        books = new Books();
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

}