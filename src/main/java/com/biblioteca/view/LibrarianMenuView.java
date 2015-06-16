package com.biblioteca.view;

import com.biblioteca.enums.LibrarianMenuItem;

import java.util.InputMismatchException;
import java.util.Scanner;

import static com.biblioteca.constants.Constants.INVALID_INPUT;
import static com.biblioteca.constants.Constants.INVALID_INPUT_TEXT;
import static com.biblioteca.enums.LibrarianMenuItem.*;

public class LibrarianMenuView {

    private Scanner scanner;

    public LibrarianMenuView(Scanner scanner) {
        this.scanner = scanner;
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
        for (LibrarianMenuItem menuItem : values()) {
            System.out.println(menuItem.getText());
        }
    }


}
