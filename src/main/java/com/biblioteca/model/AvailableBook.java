package com.biblioteca.model;

import com.biblioteca.constants.Constants;
import com.biblioteca.search.Searcher;
import com.biblioteca.visitor.Visitor;

import static com.biblioteca.constants.Constants.SUCCESSFUL_BOOK_CHECKOUT;

public class AvailableBook extends Book {

    public AvailableBook(String name, String author, int year) {
        super(name, author, year);
    }

    public AvailableBook(Book book) {
        super(book);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public Book checkoutBorrowable(User user) {
        listener.update(SUCCESSFUL_BOOK_CHECKOUT);
        return new CheckedOutBook(this);
    }

    @Override
    public void match(Searcher searcher) {
        if (searcher.getSearchString().equals(name))
            searcher.visit(this);
    }

}
