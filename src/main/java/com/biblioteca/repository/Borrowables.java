package com.biblioteca.repository;

import com.biblioteca.model.Borrowable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Borrowables<T extends Borrowable> {

    private List<T> borrowables;

    public Borrowables(List<T> borrowables) {
        this.borrowables = borrowables;
    }

    public List<T> allAvailableBooks() {
        return borrowables.stream().filter(p -> !p.isCheckedOut()).collect(Collectors.toCollection(ArrayList::new));
    }

    public boolean checkout(final String bookName) {
        Borrowable borrowable = borrowables.stream().filter(p -> p.getName().equals(bookName)).findFirst().orElse(null);
        return borrowable != null && borrowable.checkout();
    }

    public boolean returnItem(String bookName) {
        Borrowable borrowable = borrowables.stream().filter(p -> p.getName().equals(bookName)).findFirst().orElse(null);
        return borrowable != null && borrowable.returnItem();
    }

}
