package com.biblioteca.action;

import com.biblioteca.model.Borrowable;
import com.biblioteca.repository.Library;
import com.biblioteca.view.ListView;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class ListLibraryTest {

    @Mock
    Library library;

    @Mock
    ListView listView;

    Action action;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        action = new ListLibrary(library, listView, null);
    }

    @Test
    public void shouldListAllBooks() {
        action.execute();

        verify(library).allAvailableItems();
        verify(listView).displayListOfBorrowables(Matchers.anyListOf(Borrowable.class));
    }

    @Test
    public void shouldVerifyEqualsContract() {
        EqualsVerifier.forClass(ListLibrary.class).usingGetClass();
    }

}