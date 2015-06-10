package com.biblioteca.action;

import com.biblioteca.console.BibliotecaConsoleIO;
import com.biblioteca.constants.Constants;
import com.biblioteca.controller.Controller;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static com.biblioteca.enums.MenuItem.LIST_BOOKS;
import static org.mockito.Mockito.when;

public class ControllerTest {

    private Controller exceuteUserOptionsCommand;

    @Mock
    private BibliotecaConsoleIO bibliotecaConsoleIO;

    @Mock
    private Actions actions;

    @Mock
    Action mockAction;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        exceuteUserOptionsCommand = new Controller(bibliotecaConsoleIO, actions);
        when(bibliotecaConsoleIO.getUserChoice()).thenReturn(LIST_BOOKS.getCode());
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

        Mockito.verify(actions).execute(LIST_BOOKS.getCode());
    }

    @Test
    public void shouldSayInvalidInputOnInvalidInput() {
        when(bibliotecaConsoleIO.getUserChoice()).thenReturn(Constants.INVALID_INPUT);
        exceuteUserOptionsCommand.execute();

        Mockito.verify(bibliotecaConsoleIO).displayMessage(Constants.INVALID_INPUT_TEXT);
    }

}