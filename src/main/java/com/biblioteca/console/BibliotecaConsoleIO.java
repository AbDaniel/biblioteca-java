package com.biblioteca.console;

import com.biblioteca.enums.MenuItems;
import com.biblioteca.model.Book;

import java.util.List;

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
        for (MenuItems menuItems : MenuItems.values()) {
            System.out.println(menuItems.getText());
        }
    }

}
