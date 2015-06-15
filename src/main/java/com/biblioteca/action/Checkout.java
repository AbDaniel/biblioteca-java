package com.biblioteca.action;

import com.biblioteca.model.User;
import com.biblioteca.repository.Library;
import com.biblioteca.search.Searcher;

public class Checkout implements Action {

    private final Library library;
    private final User user;
    private final Searcher searcher;

    public Checkout(Library library, User user, Searcher searcher) {
        this.library = library;
        this.user = user;
        this.searcher = searcher;
    }

    @Override
    public void execute() {
        library.checkout(user, searcher);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Checkout checkout = (Checkout) o;

        if (library != null ? !library.equals(checkout.library) : checkout.library != null) return false;
        if (user != null ? !user.equals(checkout.user) : checkout.user != null) return false;
        return !(searcher != null ? !searcher.equals(checkout.searcher) : checkout.searcher != null);

    }

    @Override
    public int hashCode() {
        int result = library != null ? library.hashCode() : 0;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (searcher != null ? searcher.hashCode() : 0);
        return result;
    }
}
