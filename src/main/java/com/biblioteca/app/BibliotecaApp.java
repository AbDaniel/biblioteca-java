package com.biblioteca.app;

import com.biblioteca.controller.UserController;
import com.biblioteca.controller.LoginController;
import com.biblioteca.listener.ExitLogoutListener;
import com.biblioteca.listener.LoginListener;
import com.biblioteca.model.User;
import com.biblioteca.view.View;

import static com.biblioteca.constants.Constants.EXIT_CODE;
import static com.biblioteca.constants.Constants.LOGOUT_CODE;

public class BibliotecaApp implements ExitLogoutListener, LoginListener {

    private String welcomeMessage;
    private View view;
    private UserController userController;
    private LoginController loginController;
    private int EXIT_LOGOUT = 0;
    private User user;

    public BibliotecaApp(String welcomeMessage, View view,
                         UserController userController, LoginController loginController) {
        this.welcomeMessage = welcomeMessage;
        this.view = view;
        this.userController = userController;
        this.loginController = loginController;
        this.userController.addListener(this);
        this.loginController.addLoginListener(this);
        this.loginController.addExitLogoutListener(this);
    }

    public void start() {
        view.displayMessage(welcomeMessage);
        do {
            loginController.execute();
            do {
                userController.execute(user);
            } while (EXIT_LOGOUT != EXIT_CODE && EXIT_LOGOUT != LOGOUT_CODE);
        } while (EXIT_LOGOUT == LOGOUT_CODE);
    }

    @Override
    public void update(int value) {
        EXIT_LOGOUT = value;
    }


    @Override
    public void update(User user) {
        this.user = user;
    }
}
