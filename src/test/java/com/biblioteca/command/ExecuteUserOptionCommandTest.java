package com.biblioteca.command;

import com.biblioteca.console.BibliotecaConsoleIO;
import com.biblioteca.enums.MenuItem;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static com.biblioteca.console.BibliotecaConsoleIO.*;
import static org.mockito.Mockito.when;

public class ExecuteUserOptionCommandTest {

    private ExecuteUserOptionCommand exceuteUserOptionsCommand;

    @Mock
    private BibliotecaConsoleIO bibliotecaConsoleIO;

    @Mock
    private CommandFactory commandFactory;

    @Mock
    Command mockCommand;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        exceuteUserOptionsCommand = new ExecuteUserOptionCommand(bibliotecaConsoleIO, commandFactory);
        when(bibliotecaConsoleIO.getUserChoice()).thenReturn(1);
        when(commandFactory.getCommand(Matchers.any(MenuItem.class))).thenReturn(mockCommand);
    }

    @Test
    public void shouldListAllMenuOptions() {
        exceuteUserOptionsCommand.execute();

        Mockito.verify(bibliotecaConsoleIO).displayMenu();
    }

    @Test
    public void shouldGetMenuOptionFromUser() {
        exceuteUserOptionsCommand.execute();

        Mockito.verify(bibliotecaConsoleIO).getUserChoice();
    }

    @Test
    public void shouldExecuteCommandAssociatedWithMenuItem() {
        exceuteUserOptionsCommand.execute();

        Mockito.verify(mockCommand).execute();
    }

    @Test
    public void shouldSayInvalidInputOnInvalidInput() {
        when(bibliotecaConsoleIO.getUserChoice()).thenReturn(INVALID_INPUT);
        exceuteUserOptionsCommand.execute();

        Mockito.verify(bibliotecaConsoleIO).displayMessage(INVALID_INPUT_TEXT);
    }

}