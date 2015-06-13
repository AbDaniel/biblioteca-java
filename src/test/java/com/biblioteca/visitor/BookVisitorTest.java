package com.biblioteca.visitor;

import com.biblioteca.model.Book;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;


public class BookVisitorTest {

    BookVisitor visitor;

    @Before
    public void setUp() throws Exception {
        List<Book> books = new ArrayList<>();
        books.add(new Book("Lord of the Rings", "JR Toliken", 1930));
        books.add(new Book("Harry Potter", "JK Rowling", 1992));
        books.add(new Book("Catch-22", "Joesph Heller", 1950));
        books.add(new Book("Winds of Winter", "George RR Martin", 2017));
        visitor = new BookVisitor(books);
    }

    @Test
    public void shouldReturnBookAsString() {
        String acutalString = visitor.books(Book.REGULAR_FORMAT);

        String expectedString = "name='Lord of the Rings', author='JR Toliken', year=1930\n" +
                "name='Harry Potter', author='JK Rowling', year=1992\n" +
                "name='Catch-22', author='Joesph Heller', year=1950\n" +
                "name='Winds of Winter', author='George RR Martin', year=2017\n";

        assertEquals(expectedString, acutalString);
    }
}