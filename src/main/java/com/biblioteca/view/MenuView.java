package com.biblioteca.view;

import com.biblioteca.enums.MenuItem;

import java.util.AbstractMap.SimpleEntry;
import java.util.InputMismatchException;
import java.util.Scanner;

import static com.biblioteca.constants.Constants.INVALID_INPUT;
import static com.biblioteca.constants.Constants.INVALID_INPUT_TEXT;
import static com.biblioteca.enums.MenuItem.*;
import static java.util.Map.Entry;

public class MenuView extends View {

    private SubMenuView subMenuView;

    public MenuView(Scanner scanner, SubMenuView subMenuView) {
        super(scanner);
        this.subMenuView = subMenuView;
    }

    public Entry<MenuItem, String> getUserChoiceAsEntry() {
        int choice;
        choice = getChoice();
        MenuItem item = getMenuItem(choice);
        String name = subMenuView.getItemName(item);
        return new SimpleEntry<>(item, name);
    }

    private MenuItem getMenuItem(int choice) {
        return valueOf(choice);
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
        printInvalidMessage(choice);
        return choice;
    }

    private void printInvalidMessage(int choice) {
        scanner.nextLine();
        if (choice == INVALID_INPUT)
            System.out.println(INVALID_INPUT_TEXT);
    }

    public void displayMenu() {
        for (MenuItem menuItem : values()) {
            if (menuItem.isValidMenuItem())
                System.out.println(menuItem.getText());
        }
    }

}
