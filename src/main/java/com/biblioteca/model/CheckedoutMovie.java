package com.biblioteca.model;

import com.biblioteca.visitor.Visitor;

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
        return new AvailableMovie(this);
    }

}
