package com.biblioteca.action;

import com.biblioteca.listener.ExitLogoutListener;

import static com.biblioteca.constants.Constants.LOGOUT_CODE;

public class Logout implements Action {

    private ExitLogoutListener listener;

    @Override
    public void execute() {
        listener.update(LOGOUT_CODE);
    }

    @Override
    public void addExitLogoutListener(ExitLogoutListener exitLogoutListener) {
        this.listener = exitLogoutListener;
    }
}
