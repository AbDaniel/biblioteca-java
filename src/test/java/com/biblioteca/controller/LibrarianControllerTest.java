package com.biblioteca.controller;

import com.biblioteca.action.Action;
import com.biblioteca.parser.Parser;
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
        when(parser.getLibrarianAction(QUIT)).thenReturn(action);
        librarianController.addExitLogoutListener(listener);
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
    public void shouldAddListenerToCommand() {
        librarianController.execute(user);

        verify(action).addExitLogoutListener(listener);
    }

    @Test
    public void shouldExecuteActionSelectedByUser() {
        librarianController.execute(user);

        verify(action).execute();
    }

}