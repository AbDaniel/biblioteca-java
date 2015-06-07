package com.biblioteca.command;

import com.biblioteca.console.BibliotecaConsoleIO;
import com.biblioteca.dao.Books;
import com.biblioteca.model.Book;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class ListBooksCommandTest {

    @Mock
    Books books;
    @Mock
    BibliotecaConsoleIO bibliotecaConsoleIO;

    Command command;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        command = new ListBooksCommand(books, bibliotecaConsoleIO);
    }

    @Test
    public void shouldListAllBooks() {
        command.execute();

        verify(books).allAvialableBooks();
        verify(bibliotecaConsoleIO).displayListOfBooks(Matchers.anyListOf(Book.class));
    }

}