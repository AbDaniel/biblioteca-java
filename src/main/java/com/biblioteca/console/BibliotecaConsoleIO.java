package com.biblioteca.console;

import com.biblioteca.enums.MenuItem;
import com.biblioteca.model.Book;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class BibliotecaConsoleIO {

    public static final int INVALID_INPUT = -1;
    public static final int EXIT_STATUS = 0;

    public static final String WELCOME_TEXT = "Hello! Welcome to Biblioteca";
    public static final String INVALID_INPUT_TEXT = "Select a valid option!";
    public static final String BOOK_NAME_TEXT = "Enter a book name : ";
    public static final String BOOK_NOT_PRESENT_TEXT = "That book is not available.";
    public static final String BOOK_PRESENT_TEXT = "Thank you! Enjoy the book.";

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
        scanner.nextLine();
        return choice;
    }

    public String getBookNameFromUser() {
        return scanner.nextLine();
    }
}
