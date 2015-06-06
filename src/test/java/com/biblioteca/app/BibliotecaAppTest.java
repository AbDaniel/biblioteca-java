package com.biblioteca.app;

import com.biblioteca.command.Command;
import com.biblioteca.command.CommandFactory;
import com.biblioteca.console.BibliotecaConsoleIO;
import com.biblioteca.dao.Books;
import com.biblioteca.enums.MenuItem;
import com.biblioteca.model.Book;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static com.biblioteca.app.BibliotecaApp.WELCOME_TEXT;
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

    @Mock
    Command command;

    BibliotecaApp bibliotecaApp;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        bibliotecaApp = new BibliotecaApp(WELCOME_TEXT, books, bibliotecaConsoleIO, commandFactory);
        when(bibliotecaConsoleIO.getUserChoice()).thenReturn(1);
        when(commandFactory.getCommand(Matchers.any(MenuItem.class))).thenReturn(command);
    }

    @Test
    public void shouldPrintWelcomeMessageAtStart() {
        bibliotecaApp.start();

        Mockito.verify(bibliotecaConsoleIO).displayWelcomeMessage(WELCOME_TEXT);
    }

    @Test
    public void shouldListAllMenuOptions() {
        bibliotecaApp.start();

        Mockito.verify(bibliotecaConsoleIO).displayMenu();
    }

    @Test
    public void shouldGetMenuOptionFromUser() {
        bibliotecaApp.start();

        Mockito.verify(bibliotecaConsoleIO).getUserChoice();
    }

    @Test
    public void shouldExecuteCommandAssociatedWithMenuItem() {
        bibliotecaApp.start();

        Mockito.verify(command).execute();
    }

}