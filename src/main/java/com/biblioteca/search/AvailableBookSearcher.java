package com.biblioteca.search;

import com.biblioteca.listener.Listener;
import com.biblioteca.model.AvailableBook;

import java.util.List;

import static com.biblioteca.constants.Constants.BOOK_NOT_PRESENT_TEXT;

public class AvailableBookSearcher implements Searcher {

    private List<AvailableBook> books;
    private final String searchString;
    private Listener listener;

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
        if (books.isEmpty())
            listener.update(BOOK_NOT_PRESENT_TEXT);
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

        AvailableBookSearcher that = (AvailableBookSearcher) o;

        return !(searchString != null ? !searchString.equals(that.searchString) : that.searchString != null);

    }

    @Override
    public int hashCode() {
        return searchString != null ? searchString.hashCode() : 0;
    }
}
