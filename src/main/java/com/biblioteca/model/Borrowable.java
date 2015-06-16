package com.biblioteca.model;

import com.biblioteca.listener.Listenable;
import com.biblioteca.search.Searchable;
import com.biblioteca.visitor.Visitable;

import java.util.function.Function;

public interface Borrowable<T> extends Visitable, Listenable, Searchable {

    String toString(Function<? super T, String> format);

    default Borrowable checkoutBorrowable(User user) {
        return null;
    }

    default Borrowable returnBorrowable(User user) {
        return null;
    }

}
