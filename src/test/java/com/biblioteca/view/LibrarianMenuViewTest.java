package com.biblioteca.view;

import com.biblioteca.enums.LibrarianMenuItem;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static com.biblioteca.constants.Constants.INVALID_INPUT_TEXT;
import static org.junit.Assert.assertEquals;

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
                "2. List all Movie defaulters\n" +
                "3. Logout\n" +
                "4. Quit\n", outContent.toString());
    }

    @Test
    public void shouldGetListBooksChoiceFromUser() {
        systemInMock.provideText("1\n");
        LibrarianMenuItem expected = LibrarianMenuItem.LIST_BOOK_DEFAULTERS;

        LibrarianMenuItem actualItem = menuView.getChoice();

        assertEquals(expected, actualItem);
    }

    @Test
    public void shouldReturnInvalidInputIfUserInputsAInvalidNumber() {
        systemInMock.provideText("10\n");
        LibrarianMenuItem expected = LibrarianMenuItem.INVALID;

        LibrarianMenuItem actualItem = menuView.getChoice();

        assertEquals(expected, actualItem);
    }

    @Test
    public void shouldDisplayInvalidInputMessageOnInvalidInput() {
        systemInMock.provideText("10\n");

        menuView.getChoice();

        assertEquals(INVALID_INPUT_TEXT + "\n", outContent.toString());
    }

}