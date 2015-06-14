package com.biblioteca.view;

import com.biblioteca.listener.Listener;

import java.util.Scanner;

public class View implements Listener {

    Scanner scanner;

    public View(Scanner scanner) {
        this.scanner = scanner;
    }

    public void displayMessage(String welcomeText) {
        System.out.println(welcomeText);
    }

    public String getString() {
        return scanner.nextLine();
    }

    @Override
    public void update(String message) {
        displayMessage(message);
    }
}
