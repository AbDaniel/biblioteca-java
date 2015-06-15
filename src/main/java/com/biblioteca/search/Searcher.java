package com.biblioteca.search;

import com.biblioteca.model.Book;
import com.biblioteca.model.Borrowable;
import com.biblioteca.model.Movie;

import java.util.List;

public interface Searcher {

    String getSearchString();

    default void visit(Book book) {

    }

    default void visit(Movie movie) {

    }

    List<? extends Borrowable> searchResults();

}
