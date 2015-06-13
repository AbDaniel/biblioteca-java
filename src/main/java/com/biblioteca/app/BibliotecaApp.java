package com.biblioteca.app;

import com.biblioteca.controller.Controller;
import com.biblioteca.controller.LoginController;
import com.biblioteca.enums.MenuItem;
import com.biblioteca.model.User;
import com.biblioteca.view.View;

import static com.biblioteca.enums.MenuItem.LOGOUT;
import static com.biblioteca.enums.MenuItem.QUIT;

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
        User user;
        MenuItem chosenMenuItem;
        do {
            user = loginController.execute();
            do {
                chosenMenuItem = controller.execute(user);
            } while (chosenMenuItem != QUIT && chosenMenuItem != LOGOUT);
        } while (chosenMenuItem == LOGOUT);
    }

}
