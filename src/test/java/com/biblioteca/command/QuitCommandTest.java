package com.biblioteca.command;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import static org.junit.Assert.*;

public class QuitCommandTest {

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    Command command;

    @Before
    public void setUp() throws Exception {
        command = new QuitCommand();
    }

    @Test
    public void shouldQuitTheSystem() {
        exit.expectSystemExitWithStatus(0);

        command.execute();
    }
}