package com.biblioteca.action;

import com.biblioteca.enums.MenuItem;
import com.biblioteca.model.Owner;

import java.util.Map;

import static com.biblioteca.enums.MenuItem.QUIT;
import static com.biblioteca.enums.MenuItem.valueOf;

public class Actions {

    private Map<MenuItem, Action> actionsMap;

    public Actions(Map<MenuItem, Action> actionsMap) {
        this.actionsMap = actionsMap;
    }

    public boolean execute(int userChoice, Owner owner) {
        MenuItem selectedMenuItem = valueOf(userChoice);
        if (selectedMenuItem == QUIT)
            return false;
        actionsMap.get(selectedMenuItem).execute(owner);
        return true;
    }

}
