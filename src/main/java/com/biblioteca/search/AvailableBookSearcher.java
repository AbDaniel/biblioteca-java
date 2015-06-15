package com.biblioteca.search;

import com.biblioteca.model.AvailableBook;
import com.biblioteca.model.Book;

import java.util.List;

public class AvailableBookSearcher implements Searcher {

    private List<AvailableBook> books;
    private final String searchString;

    public AvailableBookSearcher(List<AvailableBook> books, String searchString) {
        this.books = books;
        this.searchString = searchString;
    }

    @Override
    public String getSearchString() {
        return searchString;
    }

    @Override
    public void visit(AvailableBook book) {
        books.add(book);
    }

    @Override
    public List<AvailableBook> searchResults() {
        return books;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AvailableBookSearcher that = (AvailableBookSearcher) o;

        return !(searchString != null ? !searchString.equals(that.searchString) : that.searchString != null);

    }

    @Override
    public int hashCode() {
        return searchString != null ? searchString.hashCode() : 0;
    }
}
