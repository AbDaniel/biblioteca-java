package com.biblioteca.command;

import com.biblioteca.enums.MenuItem;
import org.junit.Before;
import org.junit.Test;

import static com.biblioteca.enums.MenuItem.LIST_BOOKS;
import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CommandFactoryTest {

    CommandFactory commandFactory;

    @Before
    public void setUp() throws Exception {
        commandFactory = new CommandFactory();
    }

    @Test
    public void shouldReturnNecessaryCommand() {
        MenuItem item = LIST_BOOKS;

        ListBooksCommand actualCommoand = (ListBooksCommand) commandFactory.getCommand(item);

        assertThat(actualCommoand, is(any(ListBooksCommand.class)));
    }

}