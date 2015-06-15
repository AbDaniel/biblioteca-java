package com.biblioteca.search;

import com.biblioteca.model.Book;
import com.biblioteca.model.CheckedOutBook;

import java.util.List;

public class CheckedOutBookSearcher implements Searcher {

    private List<CheckedOutBook> books;
    private String searchString;

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

}
