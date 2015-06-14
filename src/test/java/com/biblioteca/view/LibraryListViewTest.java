package com.biblioteca.view;

import com.biblioteca.model.Book;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.*;

public class LibraryListViewTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Rule
    public final TextFromStandardInputStream systemInMock
            = TextFromStandardInputStream.emptyStandardInputStream();

    ListView listView;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        listView = new ListView(new Scanner(System.in));
    }

    @Test
    public void shouldDisplayList() {
        List<Book> books = new ArrayList<>();
        books.add(new Book("Lord of the Rings", "JR Toliken", 1930, new View(new Scanner(System.in))));
        books.add(new Book("Harry Potter", "JK Rowling", 1992, new View(new Scanner(System.in))));
        books.add(new Book("Catch-22", "Joesph Heller", 1950, new View(new Scanner(System.in))));
        books.add(new Book("Winds of Winter", "George RR Martin", 2017, new View(new Scanner(System.in))));

        listView.displayListOfBorrowables(books);
        String expected = "name='Lord of the Rings', author='JR Toliken', year=1930\n" +
                "name='Harry Potter', author='JK Rowling', year=1992\n" +
                "name='Catch-22', author='Joesph Heller', year=1950\n" +
                "name='Winds of Winter', author='George RR Martin', year=2017\n";

        assertEquals(expected, outContent.toString());
    }

}