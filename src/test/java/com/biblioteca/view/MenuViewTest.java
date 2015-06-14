package com.biblioteca.view;

import com.biblioteca.enums.MenuItem;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Scanner;

import static com.biblioteca.constants.Constants.INVALID_INPUT;
import static com.biblioteca.constants.Constants.INVALID_INPUT_TEXT;
import static com.biblioteca.enums.MenuItem.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class MenuViewTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Rule
    public final TextFromStandardInputStream systemInMock
            = TextFromStandardInputStream.emptyStandardInputStream();

    private MenuView menuView;

    @Mock
    SubMenuView subMenuView;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        menuView = new MenuView(new Scanner(System.in), subMenuView);
    }

    @Test
    public void shouldGetListBooksChoiceFromUser() {
        systemInMock.provideText("1\n");
        Map.Entry<MenuItem, String> expected = new AbstractMap.SimpleEntry<MenuItem, String>(LIST_BOOKS, null);

        Map.Entry<MenuItem, String> actualEntry = menuView.getUserChoiceAsEntry();
        assertEquals(expected, actualEntry);
    }

    @Test
    public void shouldReturnNullIfUserInputsNotANumber() {
        systemInMock.provideText("Gondor\n");

        Map.Entry<MenuItem, String> expected = null;

        Map.Entry<MenuItem, String> actualEntry = menuView.getUserChoiceAsEntry();
        assertEquals(null, actualEntry);
    }

    @Test
    public void shouldReturnInvalidInputIfUserInputsAInvalidNumber() {
        systemInMock.provideText("10\n");

        int actualUserChoice = menuView.getUserChoice();

        assertEquals(INVALID_INPUT, actualUserChoice);
    }

    @Test
    public void shouldDisplayInvalidInputMessageOnInvalidInput() {
        systemInMock.provideText("10\n");

        menuView.getUserChoice();

        assertEquals(INVALID_INPUT_TEXT + "\n", outContent.toString());
    }

    @Test
    public void shouldDisplayMenu() {
        menuView.displayMenu();

        assertEquals("1. List all books\n" +
                "2. Checkout a book\n" +
                "3. Return a book\n" +
                "4. List all Movies\n" +
                "5. Checkout a Movie\n" +
                "6. Return a Movie\n" +
                "7. Logout\n" +
                "8. Quit Biblioteca\n", outContent.toString());
    }

    @After
    public void tearDown() throws Exception {
        System.setOut(null);
    }

}