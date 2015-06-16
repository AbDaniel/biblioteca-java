package com.biblioteca.visitor;

import com.biblioteca.model.AvailableBook;
import com.biblioteca.model.AvailableMovie;
import com.biblioteca.model.Book;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

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
        visitables.add(new AvailableBook("Lord of the Rings", "JR Toliken", 1930));
        visitables.add(new AvailableBook("Harry Potter", "JK Rowling", 1992));
        visitables.add(new AvailableBook("Catch-22", "Joesph Heller", 1950));
        visitables.add(new AvailableBook("Winds of Winter", "George RR Martin", 2017));
        visitables.add(new AvailableMovie("The Matrix", "The Wachowskis", 1999, 10));
        visitor = new BookVisitor(new ArrayList<>(), REGULAR_BOOK_FORMAT);
        visitables.forEach(visitable -> visitable.accept(visitor));
    }

    @Test
    public void shouldReturnBookAsString() {
        String actualString = visitor.visitablesAsString();

        String expectedString = "";

        assertEquals(expectedString, actualString);
    }

    @Test
    public void shouldReturnSizeOfVisitablesVisited() {
        int actualSize = visitor.size();

        assertEquals(0, actualSize);
    }

    @Test
    public void shouldAddTheVisitedObjectToBooks() {
        visitor = new BookVisitor(books, REGULAR_BOOK_FORMAT);
        Book book = new AvailableBook("Winds of Winter", "George RR Martin", 2017);

        visitor.visit(book);

        verify(books).add(book);
    }

    @Test
    public void shouldVerifyEqualsContract() {
        EqualsVerifier.forClass(AvailableBookVisitor.class).usingGetClass().verify();
    }
}