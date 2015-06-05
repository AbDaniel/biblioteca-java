package com.biblioteca.console;

import com.biblioteca.model.Book;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

import java.util.ArrayList;
import java.util.List;

import static com.biblioteca.app.BibliotecaApp.*;
import static org.junit.Assert.assertEquals;

public class BibliotecaConsoleIOTest {

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    BibliotecaConsoleIO bibliotecaConsoleIO;

    @Before
    public void setUp() {
        bibliotecaConsoleIO = new BibliotecaConsoleIO();
    }

    @Test
    public void shouldDisplayWelcomeMessage() {
        bibliotecaConsoleIO.displayWelcomeMessage(WELCOME_TEXT);

        assertEquals(WELCOME_TEXT + "\n", systemOutRule.getLog());
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

        assertEquals(expected, systemOutRule.getLog());
    }

    @Test
    public void shouldDisplayMenu() {
        bibliotecaConsoleIO.displayMenu();

        assertEquals("1. List all books\n" +
                "2. Quit Biblioteca\n", systemOutRule.getLog());
       }

}