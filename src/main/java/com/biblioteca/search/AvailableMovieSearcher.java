package com.biblioteca.search;

import com.biblioteca.listener.Listener;
import com.biblioteca.model.AvailableMovie;

import java.util.List;

public class AvailableMovieSearcher implements Searcher {

    private List<AvailableMovie> movies;
    private final String searchString;
    private Listener listener;

    public AvailableMovieSearcher(List<AvailableMovie> movies, String searchString) {
        this.movies = movies;
        this.searchString = searchString;
    }

    @Override
    public String getSearchString() {
        return searchString;
    }

    @Override
    public void visit(AvailableMovie movie) {
        movies.add(movie);
    }

    @Override
    public List<AvailableMovie> searchResults() {
        return movies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AvailableMovieSearcher that = (AvailableMovieSearcher) o;

        return !(searchString != null ? !searchString.equals(that.searchString) : that.searchString != null);

    }

    @Override
    public void addListener(Listener listener) {
        this.listener = listener;
    }

    @Override
    public int hashCode() {
        return searchString != null ? searchString.hashCode() : 0;
    }
}
