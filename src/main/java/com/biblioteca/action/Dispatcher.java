package com.biblioteca.action;

import com.biblioteca.enums.MenuItem;
import com.biblioteca.model.Owner;

import java.util.Map;

import static com.biblioteca.enums.MenuItem.QUIT;
import static com.biblioteca.enums.MenuItem.valueOf;

public class Dispatcher {

    private Map<MenuItem, Action> actions;

    public Dispatcher(Map<MenuItem, Action> actions) {
        this.actions = actions;
    }

    public boolean dispatch(int userChoice, Owner owner) {
        MenuItem selectedMenuItem = valueOf(userChoice);
        if (selectedMenuItem == QUIT)
            return false;
        actions.get(selectedMenuItem).execute(owner);
        return true;
    }

}
