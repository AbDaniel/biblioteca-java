package com.biblioteca.view;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class LibrarianMenuViewTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Rule
    public final TextFromStandardInputStream systemInMock
            = TextFromStandardInputStream.emptyStandardInputStream();

    private LibrarianMenuView menuView;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        menuView = new LibrarianMenuView(new Scanner(System.in));
    }

    @Test
    public void shouldDisplayMenu() {
        menuView.displayMenu();

        assertEquals("1. List all Book defaulters\n" +
                "2. List all Movie defaulters\n", outContent.toString());
    }
}