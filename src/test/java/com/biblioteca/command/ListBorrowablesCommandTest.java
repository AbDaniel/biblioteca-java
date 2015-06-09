package com.biblioteca.command;

import com.biblioteca.console.BibliotecaConsoleIO;
import com.biblioteca.repository.Borrowables;
import com.biblioteca.model.Book;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class ListBorrowablesCommandTest {

    @Mock
    Borrowables borrowables;
    @Mock
    BibliotecaConsoleIO bibliotecaConsoleIO;

    Command command;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        command = new ListBooksCommand(borrowables, bibliotecaConsoleIO);
    }

    @Test
    public void shouldListAllBooks() {
        command.execute();

        verify(borrowables).allAvailableBooks();
        verify(bibliotecaConsoleIO).displayListOfBooks(Matchers.anyListOf(Object.class));
    }

}