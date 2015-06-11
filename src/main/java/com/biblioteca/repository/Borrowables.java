package com.biblioteca.repository;

import com.biblioteca.model.Borrowable;
import com.biblioteca.model.Owner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Borrowables<T extends Borrowable> {

    private List<T> borrowables;

    public Borrowables(List<T> borrowables) {
        this.borrowables = borrowables;
    }

    public List<T> allAvailableItems() {
        return borrowables.stream().filter(p -> !p.isCheckedOut()).collect(Collectors.toCollection(ArrayList::new));
    }

    public boolean checkout(final String itemName, Owner owner) {
        Borrowable borrowable = borrowables.stream().filter(p -> p.isEqualTo(itemName)).findFirst().orElse(null);
        return borrowable != null && borrowable.checkout(owner);
    }

    public boolean returnItem(String itemName, Owner owner) {
        Borrowable borrowable = borrowables.stream().filter(p -> p.isEqualTo(itemName)).findFirst().orElse(null);
        return borrowable != null && borrowable.returnItem(owner);
    }

}
