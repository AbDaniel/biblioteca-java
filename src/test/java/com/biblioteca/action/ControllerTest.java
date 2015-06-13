package com.biblioteca.action;

import com.biblioteca.controller.Controller;
import com.biblioteca.model.User;
import com.biblioteca.repository.Library;
import com.biblioteca.view.MenuView;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.biblioteca.enums.MenuItem.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

public class ControllerTest {

    @Mock
    private MenuView menuView;

    @Mock
    private Action mockAction;

    @Mock
    private User user;

    @Mock
    private Library library;

    @Mock
    private Action action;

    @Mock
    private Parser parser;

    private Controller controller;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        controller = new Controller(menuView, parser);
        when(menuView.getUserChoice()).thenReturn(LIST_BOOKS.getCode());
        when(parser.getAction(LIST_BOOKS, user)).thenReturn(action);
    }

    @Test
    public void shouldListAllMenuOptions() {
        controller.execute(user);

        verify(menuView).displayMenu();
    }

    @Test
    public void shouldGetMenuOptionFromUser() {
        controller.execute(user);

        verify(menuView).getUserChoice();
    }

    @Test
    public void shouldGetCommandAssociatedWithMenuItem() {
        controller.execute(user);

        verify(parser).getAction(eq(LIST_BOOKS), any(User.class));
    }

    @Test
    public void shouldNotCallDispatchIfMenuItemIsQuit() {
        when(menuView.getUserChoice()).thenReturn(QUIT.getCode());
        controller.execute(user);

        verify(parser, times(0)).getAction(eq(QUIT), any(User.class));
    }

    @Test
    public void shouldNotCallDispatchIfMenuItemIsLogout() {
        when(menuView.getUserChoice()).thenReturn(LOGOUT.getCode());
        controller.execute(user);

        verify(parser, times(0)).getAction(eq(LOGOUT), any(User.class));
    }

    @Test
    public void shouldExecuteActionSelectedByUser() {
        when(parser.getAction(LIST_BOOKS, user)).thenReturn(action);
        controller.execute(user);

        verify(action).execute();
    }

}