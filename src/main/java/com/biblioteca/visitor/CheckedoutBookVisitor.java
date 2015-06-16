package com.biblioteca.visitor;

import com.biblioteca.model.AvailableBook;
import com.biblioteca.model.Book;
import com.biblioteca.model.CheckedOutBook;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class CheckedoutBookVisitor implements Visitor<CheckedOutBook> {

    private List<CheckedOutBook> books;
    private Function<Book, String> format;

    public CheckedoutBookVisitor(List<CheckedOutBook> books, Function<Book, String> format) {
        this.format = format;
        this.books = books;
    }

    @Override
    public void visit(CheckedOutBook book) {
        books.add(book);
    }

    @Override
    public String visitablesAsString() {
        StringBuilder builder = new StringBuilder();
        books.forEach(book -> builder.append(book.toString(format)).append("\n"));
        return builder.toString();
    }

    @Override
    public List<CheckedOutBook> visitables() {
        return books;
    }

    @Override
    public boolean isEmpty() {
        return books.isEmpty();
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;

        return getClass() == o.getClass();
    }

    @Override
    public final int hashCode() {
        return 0;
    }

}
