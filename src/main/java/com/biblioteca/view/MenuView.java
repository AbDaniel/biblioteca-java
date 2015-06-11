package com.biblioteca.view;

import com.biblioteca.enums.MenuItem;

import java.util.InputMismatchException;
import java.util.Scanner;

import static com.biblioteca.constants.Constants.INVALID_INPUT;
import static com.biblioteca.constants.Constants.INVALID_INPUT_TEXT;

public class MenuView extends View {

    public MenuView(Scanner scanner) {
        super(scanner);
    }

    public int getUserChoice() {
        int choice;
        try {
            choice = scanner.nextInt();
            if (MenuItem.isInvalidMenuItem(choice)) {
                throw new InputMismatchException();
            }
        } catch (InputMismatchException e) {
            choice = INVALID_INPUT;
        }
        scanner.nextLine();
        if (choice == INVALID_INPUT)
            System.out.println(INVALID_INPUT_TEXT);
        return choice;
    }

    public void displayMenu() {
        for (MenuItem menuItem : MenuItem.values()) {
            System.out.println(menuItem.getText());
        }
    }

}
