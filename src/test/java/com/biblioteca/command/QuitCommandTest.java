package com.biblioteca.command;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.contrib.java.lang.system.SystemOutRule;

import static com.biblioteca.app.BibliotecaApp.WELCOME_TEXT;
import static com.biblioteca.command.QuitCommand.EXIT_MESSAGE;
import static org.junit.Assert.*;

public class QuitCommandTest {

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    Command command;

    @Before
    public void setUp() throws Exception {
        command = new QuitCommand(EXIT_MESSAGE);
    }

    @Test
    public void shouldPrintExitMessage() {
        command.execute();

        assertEquals(EXIT_MESSAGE + "\n", systemOutRule.getLog());

    }
}