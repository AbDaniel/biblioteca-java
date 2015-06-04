package com.biblioteca.app;

import com.biblioteca.console.BibliotecaConsoleIO;
import com.biblioteca.dao.BookDAO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static com.biblioteca.app.BibliotecaApp.WELCOME_TEXT;

public class BibliotecaAppTest {

    @Mock
    BookDAO bookDAO;
    @Mock
    BibliotecaConsoleIO bibliotecaConsoleIO;

    BibliotecaApp bibliotecaApp;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        bibliotecaApp = new BibliotecaApp(WELCOME_TEXT, bookDAO, bibliotecaConsoleIO);
    }

    @Test
    public void shouldPrintWelcomeMessage() {
        bibliotecaApp.start();

        Mockito.verify(bibliotecaConsoleIO).displayWelcomeMessage(WELCOME_TEXT);
    }

}