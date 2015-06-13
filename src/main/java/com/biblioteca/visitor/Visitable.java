package com.biblioteca.visitor;

import com.biblioteca.model.Book;
import com.biblioteca.model.Borrowable;

public interface Visitable<T extends Borrowable> {
    void accept(Visitor<T> visitor);
}
