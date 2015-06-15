package com.biblioteca.search;

import com.biblioteca.model.AvailableBook;
import com.biblioteca.model.Book;

import java.util.List;

public class AvailableBookSearcher implements Searcher {

    private List<AvailableBook> books;
    private String searchString;

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

}
