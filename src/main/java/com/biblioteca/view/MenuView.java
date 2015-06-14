package com.biblioteca.view;

import com.biblioteca.constants.Constants;
import com.biblioteca.enums.MenuItem;

import java.util.*;
import java.util.AbstractMap.SimpleEntry;

import static com.biblioteca.constants.Constants.ENTER_BOOK_NAME;
import static com.biblioteca.constants.Constants.INVALID_INPUT;
import static com.biblioteca.constants.Constants.INVALID_INPUT_TEXT;
import static com.biblioteca.enums.MenuItem.*;
import static java.util.Map.*;

public class MenuView extends View {

    private SubMenuView subMenuView;

    public MenuView(Scanner scanner, SubMenuView subMenuView) {
        super(scanner);
        this.subMenuView = subMenuView;
    }

    public int getUserChoice() {
        int choice;
        choice = getChoice();
        scanner.nextLine();
        if (choice == INVALID_INPUT)
            System.out.println(INVALID_INPUT_TEXT);
        return choice;
    }

    public Entry<MenuItem, String> getUserChoiceAsEntry() {
        int choice;
        choice = getChoice();
        MenuItem item = getMenuItem(choice);
        if (item == null)
            return null;
        String name = subMenuView.getItemName(item);
        return new SimpleEntry<>(item, name);
    }

    private MenuItem getMenuItem(int choice) {
        if (choice != INVALID_INPUT)
            return valueOf(choice);
        return null;
    }

    private int getChoice() {
        int choice;
        try {
            choice = scanner.nextInt();
            if (isInvalidMenuItem(choice)) {
                throw new InputMismatchException();
            }
        } catch (InputMismatchException e) {
            choice = INVALID_INPUT;
        }
        return choice;
    }

    public void displayMenu() {
        for (MenuItem menuItem : values()) {
            System.out.println(menuItem.getText());
        }
    }

}
