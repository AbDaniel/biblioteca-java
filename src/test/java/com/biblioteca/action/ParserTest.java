package com.biblioteca.action;

import com.biblioteca.enums.MenuItem;
import com.biblioteca.model.User;
import com.biblioteca.repository.Library;
import com.biblioteca.view.ListView;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.biblioteca.enums.MenuItem.LIST_BOOKS;
import static com.biblioteca.enums.MenuItem.QUIT;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class ParserTest {

    Parser parser;

    @Mock
    Library library;

    @Mock
    User owner;

    @Before
    public void setUp() throws Exception {
        parser = new Parser(library);
    }

    @Test
    public void shouldReturnActionBySelectedMenuItem() {
        MenuItem item = LIST_BOOKS;

        Action actualAction = parser.getAction(item, owner);
        ListLibrary expected = new ListLibrary(library, (ListView) item.view());

        assertEquals(expected, actualAction);
    }

    @Test
    public void shouldReturnNullIfSelectedMenuItemIsNotCheckoutReturnOrList() {
        MenuItem item = QUIT;

        Action actualAction = parser.getAction(item, owner);

        assertNull(actualAction);
    }

}