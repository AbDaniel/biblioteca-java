package com.biblioteca.visitor;

import com.biblioteca.model.User;

import java.util.List;

public interface DefaulterVisitor extends Visitor {

    void visit(User user, List<Visitable> visitables);

    default boolean borrowablesFound() {
        return false;
    }

}
