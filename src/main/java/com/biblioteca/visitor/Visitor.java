package com.biblioteca.visitor;

import com.biblioteca.model.Book;
import com.biblioteca.model.Movie;

public interface Visitor {

    String visitables();

    default void visit(Book book) {

    }

    default void visit(Movie movie) {

    }

}

