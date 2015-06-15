package com.biblioteca.app;

import com.biblioteca.controller.Controller;
import com.biblioteca.controller.LoginController;
import com.biblioteca.listener.Listener;
import com.biblioteca.model.User;
import com.biblioteca.view.View;

import static com.biblioteca.constants.Constants.EXIT_CODE;
import static com.biblioteca.constants.Constants.LOGOUT_CODE;

public class BibliotecaApp implements Listener {

    private String welcomeMessage;
    private View view;
    private Controller controller;
    private LoginController loginController;
    private int EXIT_LOGOUT = 0;

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
        do {
            user = loginController.execute();
            do {
                controller.execute(user);
            } while (EXIT_LOGOUT != EXIT_CODE && EXIT_LOGOUT != LOGOUT_CODE);
        } while (EXIT_LOGOUT == LOGOUT_CODE);
    }

    @Override
    public void update(int value) {
        EXIT_LOGOUT = value;
    }
}
