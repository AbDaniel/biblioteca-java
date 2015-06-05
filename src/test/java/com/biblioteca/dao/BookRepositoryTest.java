package com.biblioteca.dao;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class BookRepositoryTest {

    BookRepository bookRepository;

    @Before
    public void setUp() {
        bookRepository = BookRepository.getInstance();
    }

    @Test
    public void shouldGetInstanceOfBookDAO() {
        bookRepository = BookRepository.getInstance();

        assertThat(bookRepository, instanceOf(BookRepository.class));
    }

    @Test
    public void shouldGetSingletonInstanceOfBookDAO() {
        bookRepository = BookRepository.getInstance();
        BookRepository secondBookRepository = BookRepository.getInstance();

        assertThat(bookRepository, is(secondBookRepository));
    }

}