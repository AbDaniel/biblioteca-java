package com.biblioteca.visitor;

import com.biblioteca.model.*;

public interface Visitor {

    String visitables();

    void reset();

    default void visit(AvailableBook book) {

    }

    default void visit(CheckedOutBook book) {

    }

    default void visit(AvailableMovie movie) {

    }


    default void visit(Movie movie) {

    }

    default void visit(Book book) {

    }

}

