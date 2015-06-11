package com.biblioteca.console;

import java.util.Scanner;

public class View {

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
}
