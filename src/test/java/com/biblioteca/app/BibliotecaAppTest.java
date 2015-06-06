package com.biblioteca.app;

import com.biblioteca.command.CommandFactory;
import com.biblioteca.command.QuitCommand;
import com.biblioteca.console.BibliotecaConsoleIO;
import com.biblioteca.dao.Books;
import com.biblioteca.enums.MenuItem;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static com.biblioteca.console.BibliotecaConsoleIO.WELCOME_TEXT;
import static com.biblioteca.command.QuitCommand.EXIT_MESSAGE;
import static org.mockito.Mockito.when;

public class BibliotecaAppTest {

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    @Mock
    Books books;

    @Mock
    BibliotecaConsoleIO bibliotecaConsoleIO;

    @Mock
    CommandFactory commandFactory;

    BibliotecaApp bibliotecaApp;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        bibliotecaApp = new BibliotecaApp(WELCOME_TEXT, bibliotecaConsoleIO, commandFactory);
        when(bibliotecaConsoleIO.getUserChoice()).thenReturn(2);
        when(commandFactory.getCommand(Matchers.any(MenuItem.class))).thenReturn(new QuitCommand(EXIT_MESSAGE));
    }

    @Test
    public void shouldPrintWelcomeMessageAtStart() {
        exit.expectSystemExitWithStatus(0);
        bibliotecaApp.start();

        Mockito.verify(bibliotecaConsoleIO).displayMessage(WELCOME_TEXT);
    }

}