package com.biblioteca.action;

import com.biblioteca.model.User;
import com.biblioteca.repository.Library;
import com.biblioteca.search.BookSearcher;

import java.util.ArrayList;

public class Checkout implements Action {

    private final Library library;
    private final User user;
    private String itemName;

    public Checkout(Library library, User user, String itemName) {
        this.library = library;
        this.user = user;
        this.itemName = itemName;
    }

    @Override
    public void execute() {
        library.checkout(itemName, user, new BookSearcher(new ArrayList<>(), itemName));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Checkout checkout = (Checkout) o;

        if (library != null ? !library.equals(checkout.library) : checkout.library != null) return false;
        return !(user != null ? !user.equals(checkout.user) : checkout.user != null);

    }

    @Override
    public int hashCode() {
        int result = library != null ? library.hashCode() : 0;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }
}
