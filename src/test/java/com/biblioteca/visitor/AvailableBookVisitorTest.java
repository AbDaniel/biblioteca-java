package com.biblioteca.visitor;

import com.biblioteca.model.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static com.biblioteca.model.Book.REGULAR_BOOK_FORMAT;
import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AvailableBookVisitorTest {

    AvailableBookVisitor visitor;

    @Mock
    List<AvailableBook> books;

    @Before
    public void setUp() throws Exception {
        List<Visitable> visitables = new ArrayList<>();
        visitables.add(new AvailableBook("Lord of the Rings", "JR Toliken", 1930));
        visitables.add(new AvailableBook("Harry Potter", "JK Rowling", 1992));
        visitables.add(new AvailableBook("Catch-22", "Joesph Heller", 1950));
        visitables.add(new AvailableBook("Winds of Winter", "George RR Martin", 2017));
        visitables.add(new AvailableMovie("The Matrix", "The Wachowskis", 1999, 10));
        visitables.add(new CheckedOutBook("Dance with Dragons", "George RR Martin", 2017));
        visitor = new AvailableBookVisitor(new ArrayList<>(), REGULAR_BOOK_FORMAT);
        visitables.forEach(visitable -> visitable.accept(visitor));
    }

    @Test
    public void shouldReturnBookAsString() {
        String actualString = visitor.visitablesAsString();

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
        visitor = new AvailableBookVisitor(books, REGULAR_BOOK_FORMAT);
        AvailableBook book = new AvailableBook("Winds of Winter", "George RR Martin", 2017);

        visitor.visit(book);

        verify(books).add(book);
    }
}