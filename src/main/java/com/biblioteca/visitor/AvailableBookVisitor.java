package com.biblioteca.visitor;

import com.biblioteca.model.AvailableBook;
import com.biblioteca.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class AvailableBookVisitor implements Visitor<AvailableBook> {

    private List<AvailableBook> books;
    private Function<Book, String> format;

    public AvailableBookVisitor(List<AvailableBook> books, Function<Book, String> format) {
        this.format = format;
        this.books = books;
    }

    @Override
    public void visit(AvailableBook book) {
        books.add(book);
    }

    @Override
    public String visitablesAsString() {
        StringBuilder builder = new StringBuilder();
        books.forEach(book -> builder.append(book.toString(format)).append("\n"));
        return builder.toString();
    }

    @Override
    public List<AvailableBook> visitables() {
        return books;
    }

    public int size() {
        return books.size();
    }

}
