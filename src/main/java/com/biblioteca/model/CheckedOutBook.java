package com.biblioteca.model;

import com.biblioteca.search.Searcher;
import com.biblioteca.visitor.Visitor;

public class CheckedOutBook extends Book {

    public CheckedOutBook(String name, String author, int year) {
        super(name, author, year);
    }

    public CheckedOutBook(Book book) {
        super(book);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public Book returnBorrowable(User user) {
        return new AvailableBook(this);
    }
}
