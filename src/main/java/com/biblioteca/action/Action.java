package com.biblioteca.action;

import com.biblioteca.listener.ExitLogoutListener;

public interface Action {

    void execute();

    default void addExitLogoutListener(ExitLogoutListener exitLogoutListener) {

    }

}
