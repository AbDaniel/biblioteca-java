package com.biblioteca.action;

import com.biblioteca.model.AvailableBook;
import com.biblioteca.model.Book;
import com.biblioteca.model.Borrowable;
import com.biblioteca.repository.Library;
import com.biblioteca.view.ListView;
import com.biblioteca.visitor.AvailableBookVisitor;
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
        action = new ListLibrary(library, listView, visitor);
    }

    @Test
    public void shouldVerifyEqualsContract() {
        EqualsVerifier.forClass(ListLibrary.class).suppress(Warning.NULL_FIELDS).usingGetClass().verify();
    }

    @Test
    public void shouldGetBorrowablesFromLibraryUsingVisitor() {
        action.execute();

        verify(library).allAvailableItems(visitor);
    }

}