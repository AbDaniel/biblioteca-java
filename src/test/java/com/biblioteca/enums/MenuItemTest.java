package com.biblioteca.enums;

import org.junit.Test;

import static com.biblioteca.enums.MenuItem.INVALID;
import static com.biblioteca.enums.MenuItem.LIST_BOOKS;
import static org.junit.Assert.assertEquals;

public class MenuItemTest {

    @Test
    public void shouldPrintMenuItemMessage() {
        String actualMessage = LIST_BOOKS.getText();

        assertEquals("1. List all books", actualMessage);
    }

    @Test
    public void shouldBeAbleToGetEnumObjectUsingCode() {
        int code = 1;

        MenuItem actualMenuItem = MenuItem.valueOf(code);

        assertEquals(LIST_BOOKS, actualMenuItem);
    }

    @Test
    public void shouldReturnEmptyStringForInvalidItem() {
        String actualString = INVALID.getText();

        assertEquals("", actualString);
    }

}