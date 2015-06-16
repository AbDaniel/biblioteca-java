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
public class CheckedoutBookVisitorTest {

    CheckedoutBookVisitor visitor;

    @Mock
    List<CheckedOutBook> books;

    @Before
    public void setUp() throws Exception {
        List<Visitable> visitables = new ArrayList<>();
        visitables.add(new AvailableBook("Lord of the Rings", "JR Toliken", 1930));
        visitables.add(new AvailableBook("Harry Potter", "JK Rowling", 1992));
        visitables.add(new AvailableBook("Catch-22", "Joesph Heller", 1950));
        visitables.add(new AvailableBook("Winds of Winter", "George RR Martin", 2017));
        visitables.add(new AvailableMovie("The Matrix", "The Wachowskis", 1999, 10));
        visitables.add(new CheckedOutBook("Dance with Dragons", "George RR Martin", 2017));
        visitor = new CheckedoutBookVisitor(new ArrayList<>(), REGULAR_BOOK_FORMAT);
        visitables.forEach(visitable -> visitable.accept(visitor));
    }

    @Test
    public void shouldReturnBookAsString() {
        String actualString = visitor.visitablesAsString();

        String expectedString = "name='Dance with Dragons', author='George RR Martin', year=2017\n";

        assertEquals(expectedString, actualString);
    }

    @Test
    public void shouldAddTheVisitedObjectToBooks() {
        visitor = new CheckedoutBookVisitor(books, REGULAR_BOOK_FORMAT);
        CheckedOutBook book = new CheckedOutBook("Winds of Winter", "George RR Martin", 2017);

        visitor.visit(book);

        verify(books).add(book);
    }

}