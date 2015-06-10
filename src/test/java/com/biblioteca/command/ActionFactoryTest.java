package com.biblioteca.command;

import com.biblioteca.console.BibliotecaConsoleIO;
import com.biblioteca.model.Owner;
import com.biblioteca.repository.Borrowables;
import com.biblioteca.enums.MenuItem;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static com.biblioteca.enums.MenuItem.LIST_BOOKS;
import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ActionFactoryTest {

    Actions actions;

    @Mock
    Borrowables borrowables;

    @Mock
    Owner owner;

    @Mock
    BibliotecaConsoleIO bibliotecaConsoleIO;

    @Before
    public void setUp() throws Exception {
        actions = new Actions(borrowables, bibliotecaConsoleIO, owner);
    }

    @Test
    public void shouldReturnNecessaryCommand() {
        MenuItem item = LIST_BOOKS;

        ListBooks actualCommand = (ListBooks) actions.getCommand(item);

        assertThat(actualCommand, is(any(ListBooks.class)));
    }

}