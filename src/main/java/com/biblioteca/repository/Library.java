package com.biblioteca.repository;

import com.biblioteca.listener.Listener;
import com.biblioteca.model.Borrowable;
import com.biblioteca.model.User;
import com.biblioteca.search.Searcher;
import com.biblioteca.visitor.Visitor;

import java.util.List;

import static com.biblioteca.constants.Constants.ITEM_NOT_PRESENT;

public class Library {

    private List<Borrowable> borrowables;
    private Listener listener;

    public Library(List<Borrowable> borrowables) {
        this.borrowables = borrowables;
    }

    public List<? extends Borrowable> allAvailableItems(Visitor visitor) {
        visitor.reset();
        borrowables.forEach(borrowable -> borrowable.accept(visitor));
        listener.update(visitor.visitables());
        return null;
    }

    public boolean checkout(final String itemName, User user, Searcher searcher) {
        borrowables.forEach(borrowable -> borrowable.match(searcher));
        Borrowable borrowable = searcher.searchResults().stream().findFirst().orElse(null);
        if (borrowable == null)
            listener.update(ITEM_NOT_PRESENT);
        else {
            borrowables.remove(borrowable);
            borrowables.add(borrowable.checkoutBorrowable(user));
        }
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
