package com.biblioteca.view;

import com.biblioteca.model.Borrowable;

import java.util.List;
import java.util.Scanner;

public class ListView extends View {

    public ListView(Scanner scanner) {
        super(scanner);
    }

    public void displayListOfBorrowables(List<? extends Borrowable> borrowables) {
        for (Borrowable borrowable : borrowables) {
            System.out.println(borrowable);
        }
    }

}
