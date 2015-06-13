package com.biblioteca.model;

public class Movie {

    private final String name;
    private final String director;
    private final int year;
    private final int rating;
    private boolean checkedOut;

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

}