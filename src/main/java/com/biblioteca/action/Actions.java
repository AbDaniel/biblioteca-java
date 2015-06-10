package com.biblioteca.action;

import com.biblioteca.console.BibliotecaConsoleIO;
import com.biblioteca.model.Owner;
import com.biblioteca.repository.Borrowables;
import com.biblioteca.enums.MenuItem;

import java.util.Map;

import static com.biblioteca.enums.MenuItem.*;

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
