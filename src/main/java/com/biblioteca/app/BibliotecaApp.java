package com.biblioteca.app;

import com.biblioteca.console.View;
import com.biblioteca.controller.Controller;
import com.biblioteca.controller.LoginController;
import com.biblioteca.enums.MenuItem;
import com.biblioteca.model.Owner;

import static com.biblioteca.enums.MenuItem.*;

public class BibliotecaApp {

    private String welcomeMessage;
    private View view;
    private Controller controller;
    private LoginController loginController;

    public BibliotecaApp(String welcomeMessage, View view,
                         Controller controller, LoginController loginController) {
        this.welcomeMessage = welcomeMessage;
        this.view = view;
        this.controller = controller;
        this.loginController = loginController;
    }

    public void start() {
        view.displayMessage(welcomeMessage);
        Owner owner;
        MenuItem chosenMenuItem;
        do {
            owner = loginController.execute();
            do {
                chosenMenuItem = controller.execute(owner);
            } while (chosenMenuItem != QUIT && chosenMenuItem != LOGOUT);
        } while (chosenMenuItem == LOGOUT);
    }

}
