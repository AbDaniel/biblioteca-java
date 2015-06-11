package com.biblioteca.console;

import com.biblioteca.model.Book;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.biblioteca.constants.Constants.INVALID_INPUT;
import static com.biblioteca.constants.Constants.INVALID_INPUT_TEXT;
import static com.biblioteca.constants.Constants.WELCOME_TEXT;

import static org.junit.Assert.assertEquals;

public class BibliotecaConsoleIOTest {


    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Rule
    public final TextFromStandardInputStream systemInMock
            = TextFromStandardInputStream.emptyStandardInputStream();

    BibliotecaConsoleIO bibliotecaConsoleIO;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        bibliotecaConsoleIO = new BibliotecaConsoleIO(new Scanner(System.in));
    }

    @Test
    public void shouldDisplayWelcomeMessage() {
        bibliotecaConsoleIO.displayMessage(WELCOME_TEXT);

        assertEquals(WELCOME_TEXT + "\n", outContent.toString());
    }

    @Test
    public void shouldDisplayListOfBooks() {
        List<Book> books = new ArrayList<>();
        books.add(new Book("Lord of the Rings", "JR Toliken", 1930));
        books.add(new Book("Harry Potter", "JK Rowling", 1992));
        books.add(new Book("Catch-22", "Joesph Heller", 1950));
        books.add(new Book("Winds of Winter", "George RR Martin", 2017));

        bibliotecaConsoleIO.displayListOfBooks(books);
        String expected = "name='Lord of the Rings', author='JR Toliken', year=1930\n" +
                "name='Harry Potter', author='JK Rowling', year=1992\n" +
                "name='Catch-22', author='Joesph Heller', year=1950\n" +
                "name='Winds of Winter', author='George RR Martin', year=2017\n";

        assertEquals(expected, outContent.toString());
    }

    @Test
    public void shouldDisplayMenu() {
        bibliotecaConsoleIO.displayMenu();

        assertEquals("1. List all books\n" +
                "2. Checkout a book\n" +
                "3. Return a book\n" +
                "4. Quit Biblioteca\n", outContent.toString());
    }

    @Test
    public void shouldGetListBooksChoiceFromUser() {
        systemInMock.provideText("1\n");

        int actualUserChoice = bibliotecaConsoleIO.getUserChoice();

        assertEquals(1, actualUserChoice);
    }

    @Test
    public void shouldReturnInvalidInputIfUserInputsNotANumber() {
        systemInMock.provideText("Gondor\n");

        int actualUserChoice = bibliotecaConsoleIO.getUserChoice();

        assertEquals(INVALID_INPUT, actualUserChoice);
    }

    @Test
    public void shouldReturnInvalidInputIfUserInputsAInvalidNumber() {
        systemInMock.provideText("6\n");

        int actualUserChoice = bibliotecaConsoleIO.getUserChoice();

        assertEquals(INVALID_INPUT, actualUserChoice);
    }

    @Test
    public void shouldGetBookNameFromUser() {
        systemInMock.provideText("Lord of the Rings\n");

        String actualBookName = bibliotecaConsoleIO.getBookNameFromUser();

        assertEquals("Lord of the Rings", actualBookName);
    }

    @Test
    public void shouldDisplayInvalidInputMessageOnInvalidInput() {
        systemInMock.provideText("6\n");

        bibliotecaConsoleIO.getUserChoice();

        assertEquals(INVALID_INPUT_TEXT + "\n", outContent.toString());
    }

    @After
    public void tearDown() throws Exception {
        System.setOut(null);
    }

}