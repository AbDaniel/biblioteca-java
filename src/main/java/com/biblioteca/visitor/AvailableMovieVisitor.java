package com.biblioteca.visitor;

import com.biblioteca.model.AvailableMovie;
import com.biblioteca.model.Movie;

import java.util.List;
import java.util.function.Function;

public class AvailableMovieVisitor implements Visitor<AvailableMovie> {

    private List<AvailableMovie> movies;
    private Function<Movie, String> format;

    public AvailableMovieVisitor(List<AvailableMovie> movies, Function<Movie, String> format) {
        this.format = format;
        this.movies = movies;
    }

    @Override
    public void visit(AvailableMovie movie) {
        movies.add(movie);
    }

    @Override
    public String visitablesAsString() {
        StringBuilder builder = new StringBuilder();
        movies.forEach(book -> builder.append(book.toString(format)).append("\n"));
        return builder.toString();
    }

    @Override
    public List<AvailableMovie> visitables() {
        return movies;
    }

    public int size() {
        return movies.size();
    }

}
