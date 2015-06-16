package com.biblioteca.model;

import com.biblioteca.listener.Listener;
import com.biblioteca.search.Searcher;
import com.biblioteca.visitor.Visitor;

import java.util.function.Function;

import static com.biblioteca.constants.Constants.*;

public abstract class Movie implements Borrowable<Movie> {

    protected final String name;
    protected final String director;
    protected final int year;
    protected final int rating;
    protected boolean checkedOut;
    protected Listener listener;

    public Movie(String name, String director, int year, int rating) {
        this.name = name;
        this.director = director;
        this.year = year;
        this.rating = rating;
    }

    public Movie(Movie movie) {
        this.name = movie.name;
        this.director = movie.director;
        this.year = movie.year;
        this.rating = movie.rating;
        this.listener = movie.listener;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        return !(name != null ? !name.equals(movie.name) : movie.name != null);
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", director='" + director + '\'' +
                ", year=" + year +
                ", rating=" + rating;
    }

    @Override
    public String toString(Function<? super Movie, String> format) {
        return format.apply(this);
    }

    public static Function<Movie, String> REGULAR_MOVIE_FORMAT = movie -> "name='" + movie.name + '\'' +
            ", director='" + movie.director + '\'' +
            ", year=" + movie.year +
            ", rating=" + movie.rating;

    @Override
    public void addListener(Listener listener) {
        this.listener = listener;
    }

    @Override
    public Movie checkoutBorrowable(User user) {
        return null;
    }

    @Override
    public Movie returnBorrowable(User user) {
        return null;
    }
}