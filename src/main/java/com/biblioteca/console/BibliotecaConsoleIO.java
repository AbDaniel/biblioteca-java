package com.biblioteca.console;

import com.biblioteca.enums.MenuItem;
import com.biblioteca.model.Book;

import java.util.List;
import java.util.Scanner;

public class BibliotecaConsoleIO {

    public void displayWelcomeMessage(String welcomeMessage) {
        System.out.println(welcomeMessage);
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
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        return choice;
    }
}
