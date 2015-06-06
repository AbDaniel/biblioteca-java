package com.biblioteca.app;

import com.biblioteca.command.CommandFactory;
import com.biblioteca.command.ExecuteUserOptionCommand;
import com.biblioteca.console.BibliotecaConsoleIO;

public class BibliotecaApp {

    public static final String WELCOME_TEXT = "Hello! Welcome to Biblioteca";
    public static final String INVALID_INPUT_TEXT = "Select a valid option!";

    private String welcomeMessage;
    private BibliotecaConsoleIO bibliotecaConsoleIO;
    private CommandFactory commandFactory;

    public BibliotecaApp(String welcomeMessage, BibliotecaConsoleIO bibliotecaConsoleIO,
                         CommandFactory commandFactory) {
        this.welcomeMessage = welcomeMessage;
        this.bibliotecaConsoleIO = bibliotecaConsoleIO;
        this.commandFactory = commandFactory;
    }

    public void start() {
        bibliotecaConsoleIO.displayMessage(welcomeMessage);
        ExecuteUserOptionCommand command = new ExecuteUserOptionCommand(bibliotecaConsoleIO, commandFactory);
        do {
            command.execute();
        } while (true);
    }

}
