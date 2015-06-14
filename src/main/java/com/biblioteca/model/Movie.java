package com.biblioteca.model;

import com.biblioteca.listener.Listener;
import com.biblioteca.visitor.Visitor;

import java.util.function.Function;

public class Movie implements Borrowable<Movie> {

    private final String name;
    private final String director;
    private final int year;
    private final int rating;
    private boolean checkedOut;
    private Listener listener;

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
    public boolean isEqualTo(String itemName) {
        return name.equals(itemName);
    }

    @Override
    public boolean checkout(User user) {
        if (checkedOut) {
            return false;
        } else {
            checkedOut = true;
            user.addBorrowable(this);
            return true;
        }
    }

    @Override
    public boolean returnItem(User user) {
        if (checkedOut) {
            checkedOut = false;
            user.removeOwnable(this);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isCheckedOut() {
        return checkedOut;
    }

    @Override
    public String toString(Function<? super Movie, String> format) {
        return format.apply(this);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(new Movie(this));
    }

    public static Function<Movie, String> REGULAR_MOVIE_FORMAT = movie -> "name='" + movie.name + '\'' +
            ", director='" + movie.director + '\'' +
            ", year=" + movie.year +
            ", rating=" + movie.rating;

    @Override
    public void addListener(Listener listener) {
        this.listener = listener;
    }
}