package com.biblioteca.search;

import com.biblioteca.listener.Listener;
import com.biblioteca.model.CheckedOutBook;

import java.util.List;

public class CheckedOutBookSearcher implements Searcher {

    private List<CheckedOutBook> books;
    private final String searchString;
    private Listener listener;

    public CheckedOutBookSearcher(List<CheckedOutBook> books, String searchString) {
        this.books = books;
        this.searchString = searchString;
    }

    @Override
    public String getSearchString() {
        return searchString;
    }

    @Override
    public void visit(CheckedOutBook book) {
        books.add(book);
    }

    @Override
    public List<CheckedOutBook> searchResults() {
        return books;
    }

    @Override
    public void addListener(Listener listener) {
        this.listener = listener;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CheckedOutBookSearcher that = (CheckedOutBookSearcher) o;

        return !(searchString != null ? !searchString.equals(that.searchString) : that.searchString != null);

    }

    @Override
    public int hashCode() {
        return searchString != null ? searchString.hashCode() : 0;
    }
}
