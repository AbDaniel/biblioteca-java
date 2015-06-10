package com.biblioteca.app;

import com.biblioteca.controller.Controller;
import com.biblioteca.console.BibliotecaConsoleIO;

public class BibliotecaApp {

    private String welcomeMessage;
    private BibliotecaConsoleIO bibliotecaConsoleIO;
    private Controller controller;

    public BibliotecaApp(String welcomeMessage, BibliotecaConsoleIO bibliotecaConsoleIO,
                         Controller controller) {
        this.welcomeMessage = welcomeMessage;
        this.bibliotecaConsoleIO = bibliotecaConsoleIO;
        this.controller = controller;
    }

    public void start() {
        bibliotecaConsoleIO.displayMessage(welcomeMessage);
        boolean shouldContinue;
        do {
            shouldContinue = controller.execute();
        } while (shouldContinue);
    }

}
