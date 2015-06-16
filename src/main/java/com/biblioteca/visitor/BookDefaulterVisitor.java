package com.biblioteca.visitor;

import com.biblioteca.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.biblioteca.model.Book.REGULAR_BOOK_FORMAT;

public class BookDefaulterVisitor implements DefaulterVisitor {

    CheckedoutBookVisitor bookVisitor;
    Map<User, String> userBooks;

    public BookDefaulterVisitor(CheckedoutBookVisitor bookVisitor, Map<User, String> userBooks) {
        this.bookVisitor = bookVisitor;
        this.userBooks = userBooks;
    }

    @Override
    public void visit(User user, List<Visitable> visitables) {
        visitables.forEach(visitable -> visitable.accept(bookVisitor));
        userBooks.put(user, bookVisitor.visitablesAsString());
        bookVisitor = new CheckedoutBookVisitor(new ArrayList<>(), REGULAR_BOOK_FORMAT);
    }

    @Override
    public String visitablesAsString() {
        return userBooks.toString();
    }

    @Override
    public List<? extends Visitable> visitables() {
        return null;
    }

    @Override
    public boolean borrowablesFound() {
        return !userBooks.isEmpty();
    }

}
