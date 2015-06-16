package com.biblioteca.visitor;

import com.biblioteca.model.*;

import java.util.List;

public interface Visitor<T extends Visitable> {

    String visitablesAsString();

    List<T> visitables();

    default void visit(AvailableBook book) {

    }

    default void visit(CheckedOutBook book) {

    }

    default void visit(AvailableMovie movie) {

    }

    default void visit(CheckedoutMovie movie) {

    }

    default void visit(Movie movie) {

    }

    default void visit(Book book) {

    }

    default boolean isEmpty() {
        return true;
    }

}

