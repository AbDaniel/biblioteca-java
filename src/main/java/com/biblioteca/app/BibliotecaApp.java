package com.biblioteca.app;

import com.biblioteca.action.Actions;
import com.biblioteca.controller.Controller;
import com.biblioteca.console.BibliotecaConsoleIO;

public class BibliotecaApp {

    private String welcomeMessage;
    private BibliotecaConsoleIO bibliotecaConsoleIO;
    private Actions actions;

    public BibliotecaApp(String welcomeMessage, BibliotecaConsoleIO bibliotecaConsoleIO,
                         Actions actions) {
        this.welcomeMessage = welcomeMessage;
        this.bibliotecaConsoleIO = bibliotecaConsoleIO;
        this.actions = actions;
    }

    public void start() {
        bibliotecaConsoleIO.displayMessage(welcomeMessage);
        Controller command = new Controller(bibliotecaConsoleIO, actions);
        boolean shouldContinue = true;
        do {
            shouldContinue = command.execute();
        } while (shouldContinue);
    }

}
