package com.biblioteca.console;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static com.biblioteca.constants.Constants.INVALID_INPUT;
import static com.biblioteca.constants.Constants.INVALID_INPUT_TEXT;
import static org.junit.Assert.*;

public class MenuViewTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Rule
    public final TextFromStandardInputStream systemInMock
            = TextFromStandardInputStream.emptyStandardInputStream();

    private MenuView menuView;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        menuView = new MenuView(new Scanner(System.in));
    }

    @Test
    public void shouldGetListBooksChoiceFromUser() {
        systemInMock.provideText("1\n");

        int actualUserChoice = menuView.getUserChoice();

        assertEquals(1, actualUserChoice);
    }

    @Test
    public void shouldReturnInvalidInputIfUserInputsNotANumber() {
        systemInMock.provideText("Gondor\n");

        int actualUserChoice = menuView.getUserChoice();

        assertEquals(INVALID_INPUT, actualUserChoice);
    }

    @Test
    public void shouldReturnInvalidInputIfUserInputsAInvalidNumber() {
        systemInMock.provideText("6\n");

        int actualUserChoice = menuView.getUserChoice();

        assertEquals(INVALID_INPUT, actualUserChoice);
    }

    @Test
    public void shouldDisplayInvalidInputMessageOnInvalidInput() {
        systemInMock.provideText("6\n");

        menuView.getUserChoice();

        assertEquals(INVALID_INPUT_TEXT + "\n", outContent.toString());
    }

    @Test
    public void shouldDisplayMenu() {
        menuView.displayMenu();

        assertEquals("1. List all books\n" +
                "2. Checkout a book\n" +
                "3. Return a book\n" +
                "4. Logout\n" +
                "5. Quit Biblioteca\n", outContent.toString());
    }

    @After
    public void tearDown() throws Exception {
        System.setOut(null);
    }

}