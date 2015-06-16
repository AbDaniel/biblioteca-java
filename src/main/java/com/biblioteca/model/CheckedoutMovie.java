package com.biblioteca.model;

import com.biblioteca.constants.Constants;
import com.biblioteca.search.Searcher;
import com.biblioteca.visitor.Visitor;

import static com.biblioteca.constants.Constants.SUCCESS_MOVIE_RETURN;

public class CheckedoutMovie extends Movie {

    public CheckedoutMovie(String name, String director, int year, int rating) {
        super(name, director, year, rating);
    }

    public CheckedoutMovie(Movie movie) {
        super(movie);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public Movie returnBorrowable(User user) {
        user.removeBorrowable(this);
        listener.update(SUCCESS_MOVIE_RETURN);
        return new AvailableMovie(this);
    }

    @Override
    public void match(Searcher searcher) {
        if (searcher.getSearchString().equals(name))
            searcher.visit(this);
    }

}
