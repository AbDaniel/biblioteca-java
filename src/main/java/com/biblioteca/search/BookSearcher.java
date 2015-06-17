package com.biblioteca.search;

import com.biblioteca.listener.Listener;
import com.biblioteca.model.Book;

import java.util.List;

public class BookSearcher implements Searcher {

    private List<Book> books;
    private String searchString;
    private Listener listener;

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

    @Override
    public void addListener(Listener listener) {
        this.listener = listener;
    }

}
