package com.biblioteca.action;

import com.biblioteca.controller.Controller;
import com.biblioteca.enums.MenuItem;
import com.biblioteca.listener.Listener;
import com.biblioteca.model.User;
import com.biblioteca.repository.Library;
import com.biblioteca.view.MenuView;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.AbstractMap.SimpleEntry;

import static com.biblioteca.constants.Constants.EXIT_CODE;
import static com.biblioteca.constants.Constants.LOGOUT_CODE;
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

    private SimpleEntry<MenuItem, String> userChoice;

    @Mock
    private Listener listener;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        controller = new Controller(menuView, parser);
        userChoice = new SimpleEntry<>(LIST_BOOKS, null);
        when(menuView.getUserChoiceAsEntry()).thenReturn(userChoice);
        when(parser.getAction(userChoice, user)).thenReturn(action);
        controller.addListener(listener);
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

        verify(parser).getAction(eq(userChoice), any(User.class));
    }

    @Test
    public void shouldNotCallDispatchIfMenuItemIsQuit() {
        SimpleEntry<MenuItem, String> choice = new SimpleEntry<>(QUIT, null);
        when(menuView.getUserChoiceAsEntry()).thenReturn(choice);
        controller.execute(user);

        verify(parser, times(0)).getAction(eq(choice), any(User.class));
    }

    @Test
    public void shouldNotCallDispatchIfMenuItemIsLogout() {
        SimpleEntry<MenuItem, String> choice = new SimpleEntry<>(LOGOUT, null);
        when(menuView.getUserChoiceAsEntry()).thenReturn(choice);
        controller.execute(user);

        verify(parser, times(0)).getAction(eq(choice), any(User.class));
    }

    @Test
    public void shouldUpdateListenerIfMenuItemIsQuit() {
        SimpleEntry<MenuItem, String> choice = new SimpleEntry<>(QUIT, null);
        when(menuView.getUserChoiceAsEntry()).thenReturn(choice);
        controller.execute(user);

        verify(listener).update(EXIT_CODE);
    }

    @Test
    public void shouldUpdateIfMenuItemIsLogout() {
        SimpleEntry<MenuItem, String> choice = new SimpleEntry<>(LOGOUT, null);
        when(menuView.getUserChoiceAsEntry()).thenReturn(choice);
        controller.execute(user);

        verify(listener).update(LOGOUT_CODE);
    }

    @Test
    public void shouldExecuteActionSelectedByUser() {
        when(parser.getAction(userChoice, user)).thenReturn(action);
        controller.execute(user);

        verify(action).execute();
    }

}