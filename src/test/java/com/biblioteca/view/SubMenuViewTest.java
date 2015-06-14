package com.biblioteca.view;

import com.biblioteca.enums.MenuItem;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.Assert.*;

public class SubMenuViewTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Rule
    public final TextFromStandardInputStream systemInMock
            = TextFromStandardInputStream.emptyStandardInputStream();

    private SubMenuView view;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        view = new SubMenuView(new Scanner(System.in));
    }

    @Test
    public void shouldReturnNullIfItemHasNoSubMenu() {
        String actualString = view.getItemName(MenuItem.LIST_BOOKS);

        assertNull(actualString);
    }

    @Test
    public void shouldReturnStringGivenByUserIfItemHasSubMenu() {
        systemInMock.provideText("Lord of the Rings\n");

        String actualString = view.getItemName(MenuItem.CHECKOUT_BOOK);

        assertEquals("Lord of the Rings",actualString);
    }

}