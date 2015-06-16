package com.biblioteca.controller;

import com.biblioteca.listener.ExitLogoutListener;
import com.biblioteca.model.User;

public interface Controller {
    void execute(User user);

    void addListener(ExitLogoutListener listener);
}
