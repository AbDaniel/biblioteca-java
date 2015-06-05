package com.biblioteca.app;

import com.biblioteca.console.BibliotecaConsoleIO;
import com.biblioteca.dao.BookDAO;
import com.biblioteca.model.Book;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
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
    public void shouldPrintWelcomeMessageAtStart() {
        bibliotecaApp.start();

        Mockito.verify(bibliotecaConsoleIO).displayWelcomeMessage(WELCOME_TEXT);
    }

    @Test
    public void shouldListAllBooksAtStart() {
        bibliotecaApp.start();

        Mockito.verify(bibliotecaConsoleIO).displayListOfBooks(Matchers.anyListOf(Book.class));
    }

}