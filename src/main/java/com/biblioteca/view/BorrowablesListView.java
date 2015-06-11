package com.biblioteca.view;

import com.biblioteca.model.Borrowable;

import java.util.List;
import java.util.Scanner;

public class BorrowablesListView extends View {

    public BorrowablesListView(Scanner scanner) {
        super(scanner);
    }

    public void displayListOfBorrowables(List<? extends Borrowable> borrowables) {
        for (Borrowable borrowable : borrowables) {
            System.out.println(borrowable);
        }
    }

}
