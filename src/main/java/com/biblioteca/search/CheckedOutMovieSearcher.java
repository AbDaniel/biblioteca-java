package com.biblioteca.search;

import com.biblioteca.listener.Listener;
import com.biblioteca.model.CheckedoutMovie;

import java.util.List;

public class CheckedOutMovieSearcher implements Searcher {

    private List<CheckedoutMovie> movies;
    private final String searchString;
    private Listener listener;

    public CheckedOutMovieSearcher(List<CheckedoutMovie> movies, String searchString) {
        this.movies = movies;
        this.searchString = searchString;
    }

    @Override
    public String getSearchString() {
        return searchString;
    }

    @Override
    public void visit(CheckedoutMovie book) {
        movies.add(book);
    }

    @Override
    public List<CheckedoutMovie> searchResults() {
        return movies;
    }

    @Override
    public void addListener(Listener listener) {
        this.listener = listener;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CheckedOutMovieSearcher that = (CheckedOutMovieSearcher) o;

        return !(searchString != null ? !searchString.equals(that.searchString) : that.searchString != null);

    }

    @Override
    public int hashCode() {
        return searchString != null ? searchString.hashCode() : 0;
    }

}
