package com.biblioteca.search;

import com.biblioteca.model.Book;

import java.util.List;

public class BookSearcher implements Searcher {

    private List<Book> books;
    private String searchString;

    public BookSearcher(List<Book> books, String searchString) {
        this.books = books;
        this.searchString = searchString;
    }

    @Override
    public String getSearchString() {
        return searchString;
    }

    @Override
    public void visit(Book book) {
        books.add(book);
    }

    @Override
    public List<Book> searchResults() {
        return books;
    }

}
