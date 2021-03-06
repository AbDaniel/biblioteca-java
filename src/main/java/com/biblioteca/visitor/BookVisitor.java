package com.biblioteca.visitor;

import com.biblioteca.model.Book;
import com.biblioteca.model.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class BookVisitor implements Visitor<Book> {

    private List<Book> books;
    private Function<Book, String> format;

    public BookVisitor(List<Book> books, Function<Book, String> format) {
        this.format = format;
        this.books = books;
    }

    @Override
    public void visit(Book book) {
        books.add(book);
    }

    @Override
    public String visitablesAsString() {
        StringBuilder builder = new StringBuilder();
        books.forEach(book -> builder.append(book.toString(format)).append("\n"));
        return builder.toString();
    }

    @Override
    public List<Book> visitables() {
        return books;
    }

    public int size() {
        return books.size();
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
