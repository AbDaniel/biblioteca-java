package com.biblioteca.model;

import com.biblioteca.listener.Listener;
import com.biblioteca.search.BookSearcher;
import com.biblioteca.visitor.BookVisitor;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.biblioteca.constants.Constants.*;
import static com.biblioteca.model.Book.REGULAR_BOOK_FORMAT;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class BookTest {

    private Book book;

    @Mock
    private User user;

    @Mock
    private BookVisitor visitor;

    @Mock
    Listener listener;

    @Mock
    BookSearcher searcher;


    @Before
    public void setUp() {
        book = new AvailableBook("Lord of the Rings", "JR Toliken", 1930);
        book.addListener(listener);
    }

    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(Book.class).usingGetClass().verify();
    }

    @Test
    public void shouldReturnFormatedStringToRespresentBookState() {
        String actualString = book.toString();
        String expectedString = "name='Lord of the Rings', author='JR Toliken', year=1930";

        assertEquals(expectedString, actualString);
    }

    @Test
    public void shouldReturnStringByFormat() {
        String formattedString = book.toString(REGULAR_BOOK_FORMAT);

        String expectedString = "name='Lord of the Rings', author='JR Toliken', year=1930";

        assertEquals(expectedString, formattedString);
    }

}