package com.biblioteca.enums;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

import static com.biblioteca.enums.MenuEnum.*;
import static org.junit.Assert.*;

public class MenuEnumTest {

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();


    @Test
    public void shouldPrintMenuItemMessage() {
        String actualMessage = LIST_BOOKS.getText();
        assertEquals("1. List all books", actualMessage);
    }

}