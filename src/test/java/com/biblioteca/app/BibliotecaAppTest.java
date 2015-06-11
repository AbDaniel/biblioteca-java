package com.biblioteca.app;

import com.biblioteca.action.Dispatcher;
import com.biblioteca.console.BibliotecaConsoleIO;
import com.biblioteca.controller.Controller;
import com.biblioteca.model.Owner;
import com.biblioteca.repository.Borrowables;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.biblioteca.constants.Constants.WELCOME_TEXT;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BibliotecaAppTest {

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    @Mock
    Borrowables borrowables;

    @Mock
    BibliotecaConsoleIO bibliotecaConsoleIO;

    @Mock
    Dispatcher dispatcher;

    @Mock
    Controller controller;

    BibliotecaApp bibliotecaApp;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        bibliotecaApp = new BibliotecaApp(WELCOME_TEXT, bibliotecaConsoleIO, controller);
        when(controller.execute(any(Owner.class))).thenReturn(false);
    }

    @Test
    public void shouldPrintWelcomeMessageAtStart() {
        bibliotecaApp.start();

        verify(bibliotecaConsoleIO).displayMessage(WELCOME_TEXT);
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
        when(controller.execute(any(Owner.class))).thenReturn(true);
        Thread t = new Thread(bibliotecaApp::start);
        t.start();
        t.join(500);

        assertTrue(t.isAlive());

        t.stop();
    }

}