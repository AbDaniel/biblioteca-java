package com.biblioteca.visitor;

import com.biblioteca.model.Book;
import com.biblioteca.model.Movie;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.biblioteca.model.Book.REGULAR_BOOK_FORMAT;
import static junit.framework.TestCase.assertEquals;


public class BookVisitorTest {

    BookVisitor visitor;

    @Before
    public void setUp() throws Exception {
        List<Visitable> visitables = new ArrayList<>();
        visitables.add(new Book("Lord of the Rings", "JR Toliken", 1930));
        visitables.add(new Book("Harry Potter", "JK Rowling", 1992));
        visitables.add(new Book("Catch-22", "Joesph Heller", 1950));
        visitables.add(new Book("Winds of Winter", "George RR Martin", 2017));
        visitables.add(new Movie("The Matrix", "The Wachowskis", 1999, 10));
        visitor = new BookVisitor(REGULAR_BOOK_FORMAT);
        visitables.forEach(visitable -> visitable.accept(visitor));
    }

    @Test
    public void shouldReturnBookAsString() {
        String actualString = visitor.visitables();

        String expectedString = "name='Lord of the Rings', author='JR Toliken', year=1930\n" +
                "name='Harry Potter', author='JK Rowling', year=1992\n" +
                "name='Catch-22', author='Joesph Heller', year=1950\n" +
                "name='Winds of Winter', author='George RR Martin', year=2017\n";

        assertEquals(expectedString, actualString);
    }

    @Test
    public void shouldReturnSizeOfVisitablesVisited() {
        int actualSize = visitor.size();

        assertEquals(4, actualSize);
    }
}