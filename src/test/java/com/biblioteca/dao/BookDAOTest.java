package com.biblioteca.dao;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class BookDAOTest {

    @Test
    public void shouldGetInstanceOfBookDAO() throws Exception {
        BookDAO bookDAO = BookDAO.getInstance();

        assertThat(bookDAO, instanceOf(BookDAO.class));
    }
}