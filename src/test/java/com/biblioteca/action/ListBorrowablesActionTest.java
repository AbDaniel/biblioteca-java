package com.biblioteca.action;

import com.biblioteca.console.BorrowablesListView;
import com.biblioteca.model.Borrowable;
import com.biblioteca.model.Owner;
import com.biblioteca.repository.Borrowables;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class ListBorrowablesActionTest {

    @Mock
    Borrowables borrowables;

    @Mock
    BorrowablesListView listView;

    @Mock
    Owner owner;

    Action action;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        action = new ListBorrowables<>(borrowables, listView);
    }

    @Test
    public void shouldListAllBooks() {
        action.execute(owner);

        verify(borrowables).allAvailableItems();
        verify(listView).displayListOfBorrowables(Matchers.anyListOf(Borrowable.class));
    }

}