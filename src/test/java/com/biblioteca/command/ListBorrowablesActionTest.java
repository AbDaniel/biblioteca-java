package com.biblioteca.command;

import com.biblioteca.console.BibliotecaConsoleIO;
import com.biblioteca.repository.Borrowables;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class ListBorrowablesActionTest {

    @Mock
    Borrowables borrowables;
    @Mock
    BibliotecaConsoleIO bibliotecaConsoleIO;

    Action action;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        action = new ListBooks(borrowables, bibliotecaConsoleIO);
    }

    @Test
    public void shouldListAllBooks() {
        action.execute();

        verify(borrowables).allAvailableBooks();
        verify(bibliotecaConsoleIO).displayListOfBooks(Matchers.anyListOf(Object.class));
    }

}