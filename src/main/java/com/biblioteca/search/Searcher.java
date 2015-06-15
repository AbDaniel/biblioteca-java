package com.biblioteca.search;

import com.biblioteca.model.Book;
import com.biblioteca.model.Movie;

public interface Searcher {

    String getSearchString();

    default void visit(Book book) {

    }

    default void visit(Movie movie) {

    }

}
