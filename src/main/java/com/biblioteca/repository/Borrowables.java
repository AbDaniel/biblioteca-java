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

    public List<Object> allAvailableBooks() {
        return borrowables.stream().filter(p -> !p.isCheckedOut()).collect(Collectors.toCollection(ArrayList::new));
    }

    public boolean checkout(final String itemName) {
        Borrowable borrowable = borrowables.stream().filter(p -> p.getName().equals(itemName)).findFirst().orElse(null);
        return borrowable != null && borrowable.checkout();
    }

    public boolean returnItem(String itemName) {
        Borrowable borrowable = borrowables.stream().filter(p -> p.getName().equals(itemName)).findFirst().orElse(null);
        return borrowable != null && borrowable.returnItem();
    }

}
