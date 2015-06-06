package com.biblioteca.dao;

import com.biblioteca.model.Book;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class BooksTest {

    Books books;

    @Before
    public void setUp() {
        books = Books.getInstance();
    }

    @Test
    public void shouldGetInstanceOfBookDAO() {
        books = Books.getInstance();

        assertThat(books, instanceOf(Books.class));
    }

    @Test
    public void shouldGetSingletonInstanceOfBookDAO() {
        books = Books.getInstance();
        Books secondBooks = Books.getInstance();

        assertThat(books, is(secondBooks));
    }

    @Test
    public void shouldReturnNullIfBookIsNotAvailable() {
        String bookName = "The Fellowship Of the Ring";
        String author = "JRR Tolkien";
        int year = 1930;

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