package com.biblioteca.dao;

import org.junit.Before;
import org.junit.Test;

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

}