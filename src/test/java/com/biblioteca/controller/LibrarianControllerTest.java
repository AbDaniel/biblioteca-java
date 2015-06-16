package com.biblioteca.controller;

import com.biblioteca.action.Action;
import com.biblioteca.action.Parser;
import com.biblioteca.enums.LibrarianMenuItem;
import com.biblioteca.listener.ExitLogoutListener;
import com.biblioteca.model.User;
import com.biblioteca.repository.Library;
import com.biblioteca.view.LibrarianMenuView;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static com.biblioteca.constants.Constants.EXIT_CODE;
import static com.biblioteca.constants.Constants.LOGOUT_CODE;
import static com.biblioteca.enums.LibrarianMenuItem.LOGOUT;
import static com.biblioteca.enums.LibrarianMenuItem.QUIT;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LibrarianControllerTest {

    private LibrarianController librarianController;

    @Mock
    private LibrarianMenuView menuView;

    @Mock
    private User user;

    @Mock
    private Action mockAction;

    @Mock
    private Library library;

    @Mock
    private Action action;

    @Mock
    private Parser parser;

    @Mock
    private ExitLogoutListener listener;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        librarianController = new LibrarianController(menuView, parser);
        when(menuView.getChoice()).thenReturn(QUIT);
        librarianController.addListener(listener);
    }

    @Test
    public void shouldListAllMenuOptions() {
        librarianController.execute(user);

        verify(menuView).displayMenu();
    }

    @Test
    public void shouldGetMenuOptionFromUser() {
        librarianController.execute(user);

        verify(menuView).getChoice();
    }

    @Test
    public void shouldUpdateIfMenuItemIsLogout() {
        when(menuView.getChoice()).thenReturn(LOGOUT);
        librarianController.execute(user);

        verify(listener).update(LOGOUT_CODE);
    }

    @Test
    public void shouldUpdateIfMenuItemIsQuit() {
        when(menuView.getChoice()).thenReturn(QUIT);
        librarianController.execute(user);

        verify(listener).update(EXIT_CODE);
    }

}