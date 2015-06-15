package com.biblioteca.visitor;

import com.biblioteca.model.AvailableBook;
import com.biblioteca.model.AvailableMovie;
import com.biblioteca.model.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class AvailableMovieVisitor implements Visitor {

    private List<Movie> movies;
    private Function<Movie, String> format;

    public AvailableMovieVisitor(List<Movie> movies, Function<Movie, String> format) {
        this.format = format;
        this.movies = movies;
    }

    @Override
    public void visit(AvailableMovie movie) {
        movies.add(movie);
    }

    @Override
    public String visitables() {
        StringBuilder builder = new StringBuilder();
        movies.forEach(book -> builder.append(book.toString(format)).append("\n"));
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
