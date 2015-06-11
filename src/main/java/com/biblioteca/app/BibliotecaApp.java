package com.biblioteca.app;

import com.biblioteca.controller.Controller;
import com.biblioteca.console.BibliotecaConsoleIO;
import com.biblioteca.controller.LoginController;
import com.biblioteca.enums.MenuItem;
import com.biblioteca.model.Ownable;
import com.biblioteca.model.Owner;

import static com.biblioteca.enums.MenuItem.*;

public class BibliotecaApp {

    private String welcomeMessage;
    private BibliotecaConsoleIO bibliotecaConsoleIO;
    private Controller controller;
    private LoginController loginController;
    private MenuItem chosenMenuItem;

    public BibliotecaApp(String welcomeMessage, BibliotecaConsoleIO bibliotecaConsoleIO,
                         Controller controller, LoginController loginController) {
        this.welcomeMessage = welcomeMessage;
        this.bibliotecaConsoleIO = bibliotecaConsoleIO;
        this.controller = controller;
        this.loginController = loginController;
    }

    public void start() {
        bibliotecaConsoleIO.displayMessage(welcomeMessage);
        Owner owner;
        do {
            owner = loginController.execute();
            do {
                chosenMenuItem = controller.execute(owner);
            } while (chosenMenuItem != QUIT);
        } while (chosenMenuItem == LOGOUT);
    }

}
