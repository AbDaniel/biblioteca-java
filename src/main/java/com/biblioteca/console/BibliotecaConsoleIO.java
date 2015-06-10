package com.biblioteca.console;

import com.biblioteca.constants.Constants;
import com.biblioteca.enums.MenuItem;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class BibliotecaConsoleIO {

    private Scanner scanner;

    public BibliotecaConsoleIO(Scanner scanner) {
        this.scanner = scanner;
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displayListOfBooks(List books) {
        for (Object book : books) {
            System.out.println(book);
        }
    }

    public void displayMenu() {
        for (MenuItem menuItem : MenuItem.values()) {
            System.out.println(menuItem.getText());
        }
    }

    public int getUserChoice() {
        int choice;
        try {
            choice = scanner.nextInt();
            if (MenuItem.isInvalidMenuItem(choice)) {
                throw new InputMismatchException();
            }
        } catch (InputMismatchException e) {
            choice = Constants.INVALID_INPUT;
        }
        scanner.nextLine();
        return choice;
    }

    public String getBookNameFromUser() {
        return scanner.nextLine();
    }
}
