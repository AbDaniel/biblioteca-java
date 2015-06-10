package com.biblioteca.command;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.contrib.java.lang.system.SystemOutRule;

import static com.biblioteca.constants.Constants.EXIT_MESSAGE;
import static com.biblioteca.constants.Constants.EXIT_STATUS;
import static org.junit.Assert.assertEquals;

public class QuitTest {

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    Action action;

    @Before
    public void setUp() throws Exception {
        action = new Quit(EXIT_MESSAGE);
    }

    @Test
    public void shouldPrintExitMessage() {
        exit.expectSystemExitWithStatus(EXIT_STATUS);
        action.execute();

        assertEquals(EXIT_MESSAGE + "\n", systemOutRule.getLog());
    }
}