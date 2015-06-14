package com.biblioteca.action;

import com.biblioteca.controller.Controller;
import com.biblioteca.enums.MenuItem;
import com.biblioteca.model.User;
import com.biblioteca.repository.Library;
import com.biblioteca.view.MenuView;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.AbstractMap.SimpleEntry;

import static com.biblioteca.enums.MenuItem.*;
import static junit.framework.TestCase.assertNull;
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
        when(menuView.getUserChoiceAsEntry()).thenReturn(new SimpleEntry<>(LIST_BOOKS, null));
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

        verify(menuView).getUserChoiceAsEntry();
    }

    @Test
    public void shouldGetCommandAssociatedWithMenuItem() {
        controller.execute(user);

        verify(parser).getAction(eq(LIST_BOOKS), any(User.class));
    }

    @Test
    public void shouldNotCallDispatchIfMenuItemIsQuit() {
        when(menuView.getUserChoiceAsEntry()).thenReturn(new SimpleEntry<>(QUIT, null));
        controller.execute(user);

        verify(parser, times(0)).getAction(eq(QUIT), any(User.class));
    }

    @Test
    public void shouldNotCallDispatchIfMenuItemIsLogout() {
        when(menuView.getUserChoiceAsEntry()).thenReturn(new SimpleEntry<>(QUIT, null));
        controller.execute(user);

        verify(parser, times(0)).getAction(eq(LOGOUT), any(User.class));
    }

    @Test
    public void shouldExecuteActionSelectedByUser() {
        when(parser.getAction(LIST_BOOKS, user)).thenReturn(action);
        controller.execute(user);

        verify(action).execute();
    }

    @Test
    public void shouldReturnNullIfUserChoiceIsInvalid() {
        when(menuView.getUserChoiceAsEntry()).thenReturn(null);

        assertNull(controller.execute(user));
    }

}