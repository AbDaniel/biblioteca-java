package com.biblioteca.visitor;

import com.biblioteca.model.Book;
import com.biblioteca.model.User;

import java.util.List;

public interface DefaulterVisitor<T extends Visitable> extends Visitor<T> {

    void visit(User user, List<T> visitables);

    default boolean borrowablesFound() {
        return false;
    }

}
