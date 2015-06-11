package com.biblioteca.app;

import com.biblioteca.controller.Controller;
import com.biblioteca.console.BibliotecaConsoleIO;
import com.biblioteca.model.Ownable;
import com.biblioteca.model.Owner;

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
        Owner owner = new Owner() {
            @Override
            public void addOwnable(Ownable ownable) {

            }

            @Override
            public void removeOwnable(Ownable ownable) {

            }
        };

        do {
            shouldContinue = controller.execute(owner);
        } while (shouldContinue);
    }

}
