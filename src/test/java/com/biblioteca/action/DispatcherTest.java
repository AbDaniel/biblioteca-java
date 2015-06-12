package com.biblioteca.action;

import com.biblioteca.enums.MenuItem;
import com.biblioteca.model.Owner;
import com.biblioteca.repository.Library;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Map;

import static com.biblioteca.enums.MenuItem.LIST_BOOKS;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DispatcherTest {

    Dispatcher dispatcher;

    @Mock
    Library library;

    @Mock
    Owner owner;

    @Mock
    Map<MenuItem, Action> actionMap;

    @Mock
    Action action;

    @Before
    public void setUp() throws Exception {
        dispatcher = new Dispatcher(actionMap);
    }

    @Test
    public void shouldExecuteCommandBasedOnUserChoice() {
        MenuItem item = LIST_BOOKS;
        when(actionMap.get(item)).thenReturn(action);

        dispatcher.dispatch(item, owner);

        verify(action).execute(owner);
    }

    @Test
    public void shouldNotExecuteActionWhenChoiceIsNull() {
        dispatcher.dispatch(null, owner);

        verify(action, times(0)).execute(owner);
    }

}