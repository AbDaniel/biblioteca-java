package com.biblioteca.dao;

import com.biblioteca.model.Book;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class BookDAOTest {

    BookDAO bookDAO;

    @Before
    public void setUp() {
        bookDAO = BookDAO.getInstance();
    }

    @Test
    public void shouldGetInstanceOfBookDAO() {
        bookDAO = BookDAO.getInstance();

        assertThat(bookDAO, instanceOf(BookDAO.class));
    }

    @Test
    public void shouldGetSingletonInstanceOfBookDAO() {
        bookDAO = BookDAO.getInstance();
        BookDAO secondBookDAO = BookDAO.getInstance();

        assertThat(bookDAO, is(secondBookDAO));
    }

}