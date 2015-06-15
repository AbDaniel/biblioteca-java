package com.biblioteca.model;

import com.biblioteca.search.Searcher;
import com.biblioteca.visitor.Visitor;

public class AvailableMovie extends Movie {

    public AvailableMovie(String name, String director, int year, int rating) {
        super(name, director, year, rating);
    }

    public AvailableMovie(Movie movie) {
        super(movie);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void match(Searcher searcher) {
        if (searcher.getSearchString().equals(name))
            searcher.visit(this);
    }

    @Override
    public Movie checkoutBorrowable(User user) {
        return new CheckedoutMovie(this);
    }

}
