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
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import java.util.Scanner;

import static com.biblioteca.constants.Constants.INVALID_INPUT_TEXT;
import static com.biblioteca.enums.MenuItem.INVALID;
import static com.biblioteca.enums.MenuItem.LIST_BOOKS;
import static org.junit.Assert.assertEquals;

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
        Entry<MenuItem, String> expected = new SimpleEntry<>(LIST_BOOKS, null);

        Entry<MenuItem, String> actualEntry = menuView.getUserChoiceAsEntry();
        assertEquals(expected, actualEntry);
    }

    @Test
    public void shouldReturnNullIfUserInputsNotANumber() {
        systemInMock.provideText("Gondor\n");
        Entry<MenuItem, String> expected = new SimpleEntry<>(INVALID, null);

        Entry<MenuItem, String> actualEntry = menuView.getUserChoiceAsEntry();
        assertEquals(expected, actualEntry);
    }

    @Test
    public void shouldReturnInvalidInputIfUserInputsAInvalidNumber() {
        systemInMock.provideText("10\n");
        Entry<MenuItem, String> expected = new SimpleEntry<>(INVALID, null);

        Entry<MenuItem, String> actualEntry = menuView.getUserChoiceAsEntry();
        assertEquals(expected, actualEntry);
    }

    @Test
    public void shouldDisplayInvalidInputMessageOnInvalidInput() {
        systemInMock.provideText("10\n");

        menuView.getUserChoiceAsEntry();

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