package com.biblioteca.app;

import com.biblioteca.command.Actions;
import com.biblioteca.command.Quit;
import com.biblioteca.console.BibliotecaConsoleIO;
import com.biblioteca.repository.Borrowables;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static com.biblioteca.constants.Constants.WELCOME_TEXT;
import static com.biblioteca.enums.MenuItem.QUIT;
import static org.mockito.Mockito.when;

public class BibliotecaAppTest {

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    @Mock
    Borrowables borrowables;

    @Mock
    BibliotecaConsoleIO bibliotecaConsoleIO;

    @Mock
    Actions actions;

    BibliotecaApp bibliotecaApp;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        bibliotecaApp = new BibliotecaApp(WELCOME_TEXT, bibliotecaConsoleIO, actions);
        when(bibliotecaConsoleIO.getUserChoice()).thenReturn(QUIT.getCode());
        when(actions.execute(QUIT.getCode())).thenReturn(false);
    }

    @Test
    public void shouldPrintWelcomeMessageAtStart() {
        bibliotecaApp.start();

        Mockito.verify(bibliotecaConsoleIO).displayMessage(WELCOME_TEXT);
    }

}