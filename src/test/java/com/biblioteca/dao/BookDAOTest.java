package com.biblioteca.dao;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class BookDAOTest {

    @Test
    public void shouldGetInstanceOfBookDAO() {
        BookDAO bookDAO = BookDAO.getInstance();

        assertThat(bookDAO, instanceOf(BookDAO.class));
    }

    @Test
    public void shouldGetSingletonInstanceOfBookDAO() {
        BookDAO firstBookDAO = BookDAO.getInstance();
        BookDAO secondBookDAO = BookDAO.getInstance();

        assertThat(firstBookDAO, is(secondBookDAO));
    }
}