package com.biblioteca.action;

import com.biblioteca.model.Book;
import com.biblioteca.model.Borrowable;
import com.biblioteca.model.Movie;
import com.biblioteca.repository.Library;
import com.biblioteca.view.ListView;
import com.biblioteca.visitor.BookVisitor;
import com.biblioteca.visitor.Visitor;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static com.biblioteca.model.Book.REGULAR_BOOK_FORMAT;
import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ListLibraryTest {

    @Mock
    Library library;

    @Mock
    ListView listView;

    @Mock
    Visitor visitor;

    Action action;

    @Before
    public void setUp() throws Exception {
        action = new ListLibrary(library, listView, visitor);
    }

    public void setUpWithData() {
        List<Borrowable> bookList = new ArrayList<>();
        bookList.add(new Book("Lord of the Rings", "JR Toliken", 1930));
        bookList.add(new Book("Harry Potter", "JK Rowling", 1992));
        bookList.add(new Book("Catch-22", "Joesph Heller", 1950));
        bookList.add(new Book("Winds of Winter", "George RR Martin", 2017));
        bookList.add(new Movie("The Matrix", "The Wachowskis", 1999, 10));
        visitor = new BookVisitor(REGULAR_BOOK_FORMAT);
        action = new ListLibrary(new Library(bookList), listView, visitor);
    }

    @Test
    public void shouldListAllBooks() {
        action.execute();

        verify(library).allAvailableItems();
        verify(listView).displayListOfBorrowables(Matchers.anyListOf(Borrowable.class));
    }


    @Test
    public void shouldGetBorrowablesFromLibraryUsingVisitor() {
        action.execute();

        verify(library).getBorrowables(visitor);
    }

    @Test
    public void shouldRetrieveBorrowablesFromVisitor() {
        action.execute();

        verify(visitor).visitables();
    }

    @Test
    public void shouldRequestViewToDisplayBorrowablesRetrievedFromVisitor() {
        setUpWithData();
        action.execute();

        String expected = "name='Lord of the Rings', author='JR Toliken', year=1930\n" +
                "name='Harry Potter', author='JK Rowling', year=1992\n" +
                "name='Catch-22', author='Joesph Heller', year=1950\n" +
                "name='Winds of Winter', author='George RR Martin', year=2017\n";

        verify(listView).displayMessage(expected);
    }


    @Test
    public void shouldVerifyEqualsContract() {
        EqualsVerifier.forClass(ListLibrary.class).usingGetClass();
    }

}