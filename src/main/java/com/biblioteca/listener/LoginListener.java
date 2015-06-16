package com.biblioteca.listener;

import com.biblioteca.controller.Controller;
import com.biblioteca.model.User;

public interface LoginListener {

    void update(User user, Controller controller);

}
