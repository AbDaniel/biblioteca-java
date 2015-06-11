package com.biblioteca.view;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static com.biblioteca.constants.Constants.WELCOME_TEXT;
import static org.junit.Assert.*;

public class ViewTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Rule
    public final TextFromStandardInputStream systemInMock
            = TextFromStandardInputStream.emptyStandardInputStream();

    private View view;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        view = new View(new Scanner(System.in));
    }

    @Test
    public void shouldDisplayMessage() {
        view.displayMessage(WELCOME_TEXT);

        assertEquals(WELCOME_TEXT + "\n", outContent.toString());
    }

    @Test
    public void shouldGetRawStringFromUser() {
        systemInMock.provideText("Lord of the Rings\n");

        String actualBookName = view.getString();

        assertEquals("Lord of the Rings", actualBookName);
    }

}