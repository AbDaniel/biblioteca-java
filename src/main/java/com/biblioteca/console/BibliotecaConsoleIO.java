package com.biblioteca.console;

import com.biblioteca.enums.MenuItem;
import com.biblioteca.model.Book;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class BibliotecaConsoleIO {

    public static int INVALID_INPUT = -1;

    private Scanner scanner;

    public BibliotecaConsoleIO(Scanner scanner) {
        this.scanner = scanner;
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displayListOfBooks(List<Book> books) {
        for (Book book : books) {
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
            choice = INVALID_INPUT;
        }
        return choice;
    }
}
