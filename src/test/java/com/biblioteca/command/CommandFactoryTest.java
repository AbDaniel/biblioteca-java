package com.biblioteca.command;

import com.biblioteca.console.BibliotecaConsoleIO;
import com.biblioteca.repository.Books;
import com.biblioteca.enums.MenuItem;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static com.biblioteca.enums.MenuItem.LIST_BOOKS;
import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CommandFactoryTest {

    CommandFactory commandFactory;

    @Mock
    Books books;

    @Mock
    BibliotecaConsoleIO bibliotecaConsoleIO;

    @Before
    public void setUp() throws Exception {
        commandFactory = new CommandFactory(books, bibliotecaConsoleIO);
    }

    @Test
    public void shouldReturnNecessaryCommand() {
        MenuItem item = LIST_BOOKS;

        ListBooksCommand actualCommoand = (ListBooksCommand) commandFactory.getCommand(item);

        assertThat(actualCommoand, is(any(ListBooksCommand.class)));
    }

}