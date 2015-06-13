package com.biblioteca.visitor;

import com.biblioteca.model.Book;
import com.biblioteca.model.Movie;

public interface Visitor {

    void visit(Book book);
    void visit(Movie movie);

}
