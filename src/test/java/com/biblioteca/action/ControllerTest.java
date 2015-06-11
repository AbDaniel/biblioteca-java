package com.biblioteca.action;

import com.biblioteca.console.BibliotecaConsoleIO;
import com.biblioteca.constants.Constants;
import com.biblioteca.controller.Controller;
import com.biblioteca.model.Owner;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static com.biblioteca.enums.MenuItem.LIST_BOOKS;
import static com.biblioteca.enums.MenuItem.LOGOUT;
import static com.biblioteca.enums.MenuItem.QUIT;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ControllerTest {

    private Controller controller;

    @Mock
    private BibliotecaConsoleIO bibliotecaConsoleIO;

    @Mock
    private Dispatcher dispatcher;

    @Mock
    Action mockAction;

    @Mock
    Owner owner;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        controller = new Controller(bibliotecaConsoleIO, dispatcher);
        when(bibliotecaConsoleIO.getUserChoice()).thenReturn(LIST_BOOKS.getCode());
    }

    @Test
    public void shouldListAllMenuOptions() {
        controller.execute(owner);

        verify(bibliotecaConsoleIO).displayMenu();
    }

    @Test
    public void shouldGetMenuOptionFromUser() {
        controller.execute(owner);

        verify(bibliotecaConsoleIO).getUserChoice();
    }

    @Test
    public void shouldExecuteCommandAssociatedWithMenuItem() {
        controller.execute(owner);

        verify(dispatcher).dispatch(eq(LIST_BOOKS), any(Owner.class));
    }

    @Test
    public void shouldNotCallDispatchIfMenuItemIsQuit() {
        when(bibliotecaConsoleIO.getUserChoice()).thenReturn(QUIT.getCode());
        controller.execute(owner);

        verify(dispatcher, times(0)).dispatch(eq(QUIT), any(Owner.class));
    }

    @Test
    public void shouldNotCallDispatchIfMenuItemIsLogout() {
        when(bibliotecaConsoleIO.getUserChoice()).thenReturn(LOGOUT.getCode());
        controller.execute(owner);

        verify(dispatcher, times(0)).dispatch(eq(LOGOUT), any(Owner.class));
    }

}