package com.biblioteca.visitor;

import com.biblioteca.model.Book;
import com.biblioteca.model.Movie;
import com.biblioteca.view.View;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.biblioteca.model.Book.REGULAR_BOOK_FORMAT;
import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class BookVisitorTest {

    BookVisitor visitor;

    @Mock
    List<Book> books;

    @Before
    public void setUp() throws Exception {
        List<Visitable> visitables = new ArrayList<>();
        visitables.add(new Book("Lord of the Rings", "JR Toliken", 1930, new View(new Scanner(System.in))));
        visitables.add(new Book("Harry Potter", "JK Rowling", 1992, new View(new Scanner(System.in))));
        visitables.add(new Book("Catch-22", "Joesph Heller", 1950, new View(new Scanner(System.in))));
        visitables.add(new Book("Winds of Winter", "George RR Martin", 2017, new View(new Scanner(System.in))));
        visitables.add(new Movie("The Matrix", "The Wachowskis", 1999, 10));
        visitor = new BookVisitor(new ArrayList<>(), REGULAR_BOOK_FORMAT);
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

    @Test
    public void shouldAddTheVisitedObjectToBooks() {
        visitor = new BookVisitor(books, REGULAR_BOOK_FORMAT);
        Book book = new Book("Winds of Winter", "George RR Martin", 2017, new View(new Scanner(System.in)));

        visitor.visit(book);

        verify(books).add(book);
    }
}