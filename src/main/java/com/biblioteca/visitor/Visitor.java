package com.biblioteca.visitor;

import com.biblioteca.model.AvailableBook;
import com.biblioteca.model.Book;
import com.biblioteca.model.CheckedOutBook;
import com.biblioteca.model.Movie;

public interface Visitor {

    String visitables();

    void reset();

    default void visit(AvailableBook book) {

    }

    default void visit(CheckedOutBook book) {

    }

    default void visit(Movie movie) {

    }

    default void visit(Book book) {

    }

}

