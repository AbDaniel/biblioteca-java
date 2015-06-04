package com.biblioteca.app;

import com.biblioteca.dao.BookDAO;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;

public class BibliotecaAppTest {

    @Test
    public void shouldPrintWelcomeMessage() {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        BibliotecaApp bibliotecaApp = new BibliotecaApp(BibliotecaApp.WELCOME_TEXT, any(BookDAO.class));
        bibliotecaApp.start();

        assertThat(outContent.toString(), is(BibliotecaApp.WELCOME_TEXT + "\n"));
    }

}