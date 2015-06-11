package com.biblioteca.action;

import com.biblioteca.enums.MenuItem;
import com.biblioteca.model.Owner;

import java.util.Map;

public class Dispatcher {

    private Map<MenuItem, Action> actions;

    public Dispatcher(Map<MenuItem, Action> actions) {
        this.actions = actions;
    }

    public void dispatch(MenuItem selectedMenuItem, Owner owner) {
        if (selectedMenuItem != null)
            actions.get(selectedMenuItem).execute(owner);
    }

}
