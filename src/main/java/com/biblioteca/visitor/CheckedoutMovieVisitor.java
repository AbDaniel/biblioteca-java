package com.biblioteca.visitor;

import com.biblioteca.model.CheckedoutMovie;
import com.biblioteca.model.Movie;

import java.util.List;
import java.util.function.Function;

public class CheckedoutMovieVisitor implements Visitor<CheckedoutMovie> {

    private List<CheckedoutMovie> movies;
    private Function<Movie, String> format;

    public CheckedoutMovieVisitor(List<CheckedoutMovie> movies, Function<Movie, String> format) {
        this.format = format;
        this.movies = movies;
    }

    @Override
    public void visit(CheckedoutMovie movie) {
        movies.add(movie);
    }

    @Override
    public String visitablesAsString() {
        StringBuilder builder = new StringBuilder();
        movies.forEach(movie -> builder.append(movie.toString(format)).append("\n"));
        return builder.toString();
    }

    @Override
    public List<CheckedoutMovie> visitables() {
        return movies;
    }

    @Override
    public boolean isEmpty() {
        return movies.isEmpty();
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;

        return getClass() == o.getClass();
    }

    @Override
    public final int hashCode() {
        return 0;
    }

}
