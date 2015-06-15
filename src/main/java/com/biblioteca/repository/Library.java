package com.biblioteca.repository;

import com.biblioteca.constants.Constants;
import com.biblioteca.listener.Listener;
import com.biblioteca.model.Borrowable;
import com.biblioteca.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Library {

    private List<Borrowable> borrowables;
    private Listener listener;

    public Library(List<Borrowable> borrowables) {
        this.borrowables = borrowables;
    }

    public List<? extends Borrowable> allAvailableItems() {
        return borrowables.stream().filter(p -> !p.isCheckedOut()).collect(Collectors.toCollection(ArrayList::new));
    }

    public boolean checkout(final String itemName, User user) {
        Borrowable borrowable = borrowables.stream().filter(p -> p.isEqualTo(itemName)).findFirst().orElse(null);
        if (borrowable == null)
            listener.update(Constants.ITEM_NOT_PRESENT);
        return borrowable != null && borrowable.checkout(user);
    }

    public boolean returnItem(String itemName, User user) {
        Borrowable borrowable = borrowables.stream().filter(p -> p.isEqualTo(itemName)).findFirst().orElse(null);
        return borrowable != null && borrowable.returnItem(user);
    }

    public void addListener(Listener listener) {
        this.listener = listener;
    }

}
