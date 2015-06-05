package com.biblioteca.console;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

import static com.biblioteca.app.BibliotecaApp.*;
import static org.junit.Assert.assertEquals;

public class BibliotecaConsoleIOTest {

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    BibliotecaConsoleIO bibliotecaConsoleIO;

    @Before
    public void setUp() {
        bibliotecaConsoleIO = new BibliotecaConsoleIO();
    }

    @Test
    public void shouldDisplayWelcomeMessage() {
        bibliotecaConsoleIO.displayWelcomeMessage(WELCOME_TEXT);

        assertEquals(WELCOME_TEXT + "\n", systemOutRule.getLog());
    }

    @Test
    public void shouldDisplayListOfBooks() {

    }

}