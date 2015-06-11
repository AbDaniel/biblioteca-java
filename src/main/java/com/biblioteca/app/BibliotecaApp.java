package com.biblioteca.app;

import com.biblioteca.controller.Controller;
import com.biblioteca.console.BibliotecaConsoleIO;
import com.biblioteca.enums.MenuItem;
import com.biblioteca.model.Ownable;
import com.biblioteca.model.Owner;

import static com.biblioteca.enums.MenuItem.*;

public class BibliotecaApp {

    private String welcomeMessage;
    private BibliotecaConsoleIO bibliotecaConsoleIO;
    private Controller controller;
    private boolean shouldContinue;
    private MenuItem chosenMenuItem;

    public BibliotecaApp(String welcomeMessage, BibliotecaConsoleIO bibliotecaConsoleIO,
                         Controller controller) {
        this.welcomeMessage = welcomeMessage;
        this.bibliotecaConsoleIO = bibliotecaConsoleIO;
        this.controller = controller;
    }

    public void start() {
        bibliotecaConsoleIO.displayMessage(welcomeMessage);
        Owner owner = new Owner() {
            @Override
            public void addOwnable(Ownable ownable) {

            }

            @Override
            public void removeOwnable(Ownable ownable) {

            }
        };

        do {
            chosenMenuItem = controller.execute(owner);
        } while (chosenMenuItem != QUIT);
    }

}
