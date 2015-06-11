package com.biblioteca.app;

import com.biblioteca.action.Dispatcher;
import com.biblioteca.console.View;
import com.biblioteca.controller.Controller;
import com.biblioteca.controller.LoginController;
import com.biblioteca.model.Owner;
import com.biblioteca.repository.Borrowables;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.biblioteca.constants.Constants.WELCOME_TEXT;
import static com.biblioteca.enums.MenuItem.*;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BibliotecaAppTest {

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    @Mock
    Borrowables borrowables;

    @Mock
    View view;

    @Mock
    Dispatcher dispatcher;

    @Mock
    Controller controller;

    @Mock
    LoginController loginController;

    BibliotecaApp bibliotecaApp;

    @Before
    public void setUp() {
        bibliotecaApp = new BibliotecaApp(WELCOME_TEXT, view, controller, loginController);
        when(controller.execute(any(Owner.class))).thenReturn(QUIT);
    }

    @Test
    public void shouldPrintWelcomeMessageAtStart() {
        bibliotecaApp.start();

        verify(view).displayMessage(WELCOME_TEXT);
    }

    @Test
    public void shouldStartController() {
        bibliotecaApp.start();

        verify(controller).execute(any(Owner.class));
    }

    @Test
    public void shouldExitApp() {
        bibliotecaApp.start();

        verify(controller, times(1)).execute(any(Owner.class));
    }

    @Test
    public void shouldContinueWhenUserDoesNotExit() throws InterruptedException {
        when(controller.execute(any(Owner.class))).thenReturn(LIST_BOOKS);
        Thread t = new Thread(bibliotecaApp::start);
        t.start();
        t.join(500);

        assertTrue(t.isAlive());

        t.stop();
    }

    @Test
    public void shouldExecuteLoginController() {
        bibliotecaApp.start();

        verify(loginController).execute();
    }

}