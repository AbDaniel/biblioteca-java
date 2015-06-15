package com.biblioteca.visitor;

import com.biblioteca.model.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class MovieVisitor implements Visitor {

    private List<Movie> movies;
    private Function<Movie, String> format;

    public MovieVisitor(List<Movie> movies, Function<Movie, String> format) {
        this.format = format;
        this.movies = movies;
    }

    @Override
    public void visit(Movie movie) {
        movies.add(movie);
    }

    @Override
    public String visitables() {
        StringBuilder builder = new StringBuilder();
        movies.forEach(movie -> builder.append(movie.toString(format)).append("\n"));
        return builder.toString();
    }

    @Override
    public void reset() {
        movies = new ArrayList<>();
    }

    public int size() {
        return movies.size();
    }
}
