package com.biblioteca.action;

import com.biblioteca.view.BorrowablesListView;
import com.biblioteca.model.Borrowable;
import com.biblioteca.model.Owner;
import com.biblioteca.repository.Library;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class ListLibraryActionTest {

    @Mock
    Library library;

    @Mock
    BorrowablesListView listView;

    @Mock
    Owner owner;

    Action action;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        action = new ListBorrowables<>(library, listView);
    }

    @Test
    public void shouldListAllBooks() {
        action.execute(owner);

        verify(library).allAvailableItems();
        verify(listView).displayListOfBorrowables(Matchers.anyListOf(Borrowable.class));
    }

}