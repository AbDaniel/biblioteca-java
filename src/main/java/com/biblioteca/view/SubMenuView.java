package com.biblioteca.view;

import com.biblioteca.enums.MenuItem;

import java.util.Scanner;

public class SubMenuView extends View {

    public SubMenuView(Scanner scanner) {
        super(scanner);
    }

    public String getItemName(MenuItem item) {
        if (hasSubMenu(item)) {
            displayMessage(item.getPromptText());
            return getString();
        }
        return null;
    }

    private boolean hasSubMenu(MenuItem item) {
        return item.getPromptText() != null;
    }

}
