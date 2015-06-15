package com.biblioteca.listener;

import com.biblioteca.controller.Controller;
import com.biblioteca.model.User;

public interface Listener {
    default void update(String message) {

    }

    default void update(int value) {

    }

    default void update(User user, Controller controller) {

    }
}
