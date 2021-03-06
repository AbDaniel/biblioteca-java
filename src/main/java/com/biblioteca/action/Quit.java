package com.biblioteca.action;

import com.biblioteca.listener.ExitLogoutListener;

import static com.biblioteca.constants.Constants.EXIT_CODE;

public class Quit implements Action {

    private ExitLogoutListener listener;

    @Override
    public void execute() {
        listener.update(EXIT_CODE);
    }

    @Override
    public void addExitLogoutListener(ExitLogoutListener exitLogoutListener) {
        this.listener = exitLogoutListener;
    }

    @Override
    public boolean equals(Object o) {
        return this == o || !(o == null || getClass() != o.getClass());
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
