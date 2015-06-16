package com.biblioteca.visitor;

import com.biblioteca.model.Borrowable;
import com.biblioteca.model.User;

import java.util.List;

public interface DefaulterVisitor extends Visitor {

    void visit(User user, List<? extends Borrowable> visitables);

    default boolean borrowablesFound() {
        return false;
    }

}
