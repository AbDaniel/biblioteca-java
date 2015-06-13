package com.biblioteca.repository;

import com.biblioteca.model.Borrowable;
import com.biblioteca.model.User;
import com.biblioteca.visitor.Visitor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Library {

    private List<Borrowable> borrowables;

    public Library(List<Borrowable> borrowables) {
        this.borrowables = borrowables;
    }

    public List<? extends Borrowable> allAvailableItems() {
        return borrowables.stream().filter(p -> !p.isCheckedOut()).collect(Collectors.toCollection(ArrayList::new));
    }

    public boolean checkout(final String itemName, User user) {
        Borrowable borrowable = borrowables.stream().filter(p -> p.isEqualTo(itemName)).findFirst().orElse(null);
        return borrowable != null && borrowable.checkout(user);
    }

    public boolean returnItem(String itemName, User user) {
        Borrowable borrowable = borrowables.stream().filter(p -> p.isEqualTo(itemName)).findFirst().orElse(null);
        return borrowable != null && borrowable.returnItem(user);
    }

    public void getAvailableBorrowables(Visitor visitor) {
        borrowables.stream().filter(p -> !p.isCheckedOut()).forEach(borrowable -> borrowable.accept(visitor));
    }
}
