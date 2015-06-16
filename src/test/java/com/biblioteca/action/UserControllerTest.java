package com.biblioteca.action;

import com.biblioteca.controller.UserController;
import com.biblioteca.enums.MenuItem;
import com.biblioteca.listener.ExitLogoutListener;
import com.biblioteca.model.User;
import com.biblioteca.repository.Library;
import com.biblioteca.view.MenuView;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.AbstractMap.SimpleEntry;

import static com.biblioteca.constants.Constants.*;
import static com.biblioteca.enums.MenuItem.*;
import static junit.framework.TestCase.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

public class UserControllerTest {

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

    private UserController userController;

    private SimpleEntry<MenuItem, String> userChoice;

    @Mock
    private ExitLogoutListener listener;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        userController = new UserController(menuView, parser);
        userChoice = new SimpleEntry<>(LIST_BOOKS, null);
        when(menuView.getUserChoiceAsEntry()).thenReturn(userChoice);
        when(parser.getAction(userChoice, user)).thenReturn(action);
        userController.addListener(listener);
    }

    @Test
    public void shouldListAllMenuOptions() {
        userController.execute(user);

        verify(menuView).displayMenu();
    }

    @Test
    public void shouldGetMenuOptionFromUser() {
        userController.execute(user);

        verify(menuView).getUserChoiceAsEntry();
    }

    @Test
    public void shouldGetCommandAssociatedWithMenuItem() {
        userController.execute(user);

        verify(parser).getAction(eq(userChoice), any(User.class));
    }

    @Test
    public void shouldNotCallDispatchIfMenuItemIsQuit() {
        SimpleEntry<MenuItem, String> choice = new SimpleEntry<>(QUIT, null);
        when(menuView.getUserChoiceAsEntry()).thenReturn(choice);
        userController.execute(user);

        verify(parser, times(0)).getAction(eq(choice), any(User.class));
    }

    @Test
    public void shouldNotCallDispatchIfMenuItemIsLogout() {
        SimpleEntry<MenuItem, String> choice = new SimpleEntry<>(LOGOUT, null);
        when(menuView.getUserChoiceAsEntry()).thenReturn(choice);
        userController.execute(user);

        verify(parser, times(0)).getAction(eq(choice), any(User.class));
    }

    @Test
    public void shouldUpdateListenerIfMenuItemIsQuit() {
        SimpleEntry<MenuItem, String> choice = new SimpleEntry<>(QUIT, null);
        when(menuView.getUserChoiceAsEntry()).thenReturn(choice);
        userController.execute(user);

        verify(listener).update(EXIT_CODE);
    }

    @Test
    public void shouldUpdateIfMenuItemIsLogout() {
        SimpleEntry<MenuItem, String> choice = new SimpleEntry<>(LOGOUT, null);
        when(menuView.getUserChoiceAsEntry()).thenReturn(choice);
        userController.execute(user);

        verify(listener).update(LOGOUT_CODE);
    }

    @Test
    public void shouldExecuteActionSelectedByUser() {
        when(parser.getAction(userChoice, user)).thenReturn(action);
        userController.execute(user);

        verify(action).execute();
    }

}