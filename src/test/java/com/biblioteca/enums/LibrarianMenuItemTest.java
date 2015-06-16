package com.biblioteca.enums;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static com.biblioteca.enums.LibrarianMenuItem.LIST_BOOK_DEFAULTERS;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class LibrarianMenuItemTest {

    @Test
    public void shouldPrintMenuItemMessage() {
        String actualMessage = LIST_BOOK_DEFAULTERS.getText();

        assertEquals("1. List all Book defaulters", actualMessage);
    }

    @Test
    public void shouldBeAbleToGetEnumObjectUsingCode() {
        int code = 1;

        LibrarianMenuItem actualMenuItem = LibrarianMenuItem.valueOf(code);

        assertEquals(LIST_BOOK_DEFAULTERS, actualMenuItem);
    }

}