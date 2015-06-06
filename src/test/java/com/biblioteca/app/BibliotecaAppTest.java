package com.biblioteca.app;

import com.biblioteca.console.BibliotecaConsoleIO;
import com.biblioteca.dao.Books;
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
import static com.biblioteca.enums.MenuItem.LIST_BOOKS;
import static com.biblioteca.enums.MenuItem.QUIT;
import static org.mockito.Mockito.when;

public class BibliotecaAppTest {

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    @Mock
    Books books;
    @Mock
    BibliotecaConsoleIO bibliotecaConsoleIO;

    BibliotecaApp bibliotecaApp;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        bibliotecaApp = new BibliotecaApp(WELCOME_TEXT, books, bibliotecaConsoleIO);
        when(bibliotecaConsoleIO.getMenuItemFromConsole()).thenReturn(LIST_BOOKS);
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

        Mockito.verify(bibliotecaConsoleIO).getMenuItemFromConsole();
    }

    @Test
    public void shouldListAllBooksWhenListBookIsChosenFromMenu() {
        when(bibliotecaConsoleIO.getMenuItemFromConsole()).thenReturn(LIST_BOOKS);
        bibliotecaApp.start();

        Mockito.verify(bibliotecaConsoleIO).displayListOfBooks(Matchers.anyListOf(Book.class));
    }

    @Test
    public void shouldQuitWhenQuitIsChosenFromTheMenu() {
        exit.expectSystemExitWithStatus(0);
        when(bibliotecaConsoleIO.getMenuItemFromConsole()).thenReturn(QUIT);

        bibliotecaApp.start();
    }

}