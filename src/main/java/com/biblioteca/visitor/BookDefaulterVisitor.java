package com.biblioteca.visitor;

import com.biblioteca.model.CheckedOutBook;
import com.biblioteca.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.biblioteca.model.Book.REGULAR_BOOK_FORMAT;

public class BookDefaulterVisitor implements DefaulterVisitor<CheckedOutBook> {

    CheckedoutBookVisitor bookVisitor;
    Map<User, String> userBooks;

    public BookDefaulterVisitor(CheckedoutBookVisitor bookVisitor, Map<User, String> userBooks) {
        this.bookVisitor = bookVisitor;
        this.userBooks = userBooks;
    }

    @Override
    public String visitablesAsString() {
        StringBuilder builder = new StringBuilder();
        userBooks.forEach((user, s) -> builder.append(user.toString()).append("\n").append(s));
        return builder.toString();
    }

    @Override
    public void visit(User user, List<CheckedOutBook> visitables) {
        visitables.forEach(p -> p.accept(bookVisitor));
        userBooks.put(user, bookVisitor.visitablesAsString());
        bookVisitor = new CheckedoutBookVisitor(new ArrayList<>(), REGULAR_BOOK_FORMAT);
    }

    @Override
    public List<CheckedOutBook> visitables() {
        return null;
    }

    @Override
    public boolean borrowablesFound() {
        return !userBooks.isEmpty();
    }

}
