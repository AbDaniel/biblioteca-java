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

import static com.biblioteca.enums.MenuItem.LIST_BOOKS;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
        userController.addExitLogoutListener(listener);
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
    public void shouldExecuteActionSelectedByUser() {
        when(parser.getAction(userChoice, user)).thenReturn(action);
        userController.execute(user);

        verify(action).execute();
    }

    @Test
    public void shouldAddListenerToCommand() {
        when(parser.getAction(userChoice, user)).thenReturn(action);
        userController.execute(user);

        verify(action).addExitLogoutListener(listener);
    }

}