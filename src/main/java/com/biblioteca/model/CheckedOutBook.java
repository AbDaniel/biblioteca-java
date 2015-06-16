package com.biblioteca.model;

import com.biblioteca.search.Searcher;
import com.biblioteca.visitor.Visitor;

import static com.biblioteca.constants.Constants.SUCCESSFUL_BOOK_RETURN;

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
        user.removeBorrowable(this);
        listener.update(SUCCESSFUL_BOOK_RETURN);
        return new AvailableBook(this);
    }

    @Override
    public void match(Searcher searcher) {
        if (searcher.getSearchString().equals(name))
            searcher.visit(this);
    }

}
