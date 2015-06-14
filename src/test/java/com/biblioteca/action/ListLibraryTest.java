package com.biblioteca.action;

import com.biblioteca.model.Book;
import com.biblioteca.model.Borrowable;
import com.biblioteca.repository.Library;
import com.biblioteca.view.ListView;
import com.biblioteca.visitor.BookVisitor;
import com.biblioteca.visitor.Visitor;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static com.biblioteca.model.Book.REGULAR_BOOK_FORMAT;
import static org.mockito.Mockito.verify;

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
        MockitoAnnotations.initMocks(this);
        action = new ListLibrary(library, listView);
    }

    public void setUpWithData() {
        List<Borrowable> bookList = new ArrayList<>();
        bookList.add(new Book("Lord of the Rings", "JR Toliken", 1930));
        bookList.add(new Book("Harry Potter", "JK Rowling", 1992));
        bookList.add(new Book("Catch-22", "Joesph Heller", 1950));
        bookList.add(new Book("Winds of Winter", "George RR Martin", 2017));
        visitor = new BookVisitor(new ArrayList<>(), REGULAR_BOOK_FORMAT);
        action = new ListLibrary(new Library(bookList), listView);
    }

    @Test
    public void shouldVerifyEqualsContract() {
        EqualsVerifier.forClass(ListLibrary.class).suppress(Warning.NULL_FIELDS).usingGetClass().verify();
    }

    @Test
    public void shouldGetBorrowablesFromLibraryUsingVisitor() {
        action.execute();

        verify(library).allAvailableItems();
    }

    @Test
    public void shouldRequestViewToDisplayBorrowablesRetrievedFromVisitor() {
        setUpWithData();
        action.execute();

        List<Borrowable> expected = new ArrayList<>();
        expected.add(new Book("Lord of the Rings", "JR Toliken", 1930));
        expected.add(new Book("Harry Potter", "JK Rowling", 1992));
        expected.add(new Book("Catch-22", "Joesph Heller", 1950));
        expected.add(new Book("Winds of Winter", "George RR Martin", 2017));

        verify(listView).displayListOfBorrowables(expected);
    }

}