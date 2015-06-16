package com.biblioteca.visitor;

import com.biblioteca.model.Borrowable;
import com.biblioteca.model.CheckedOutBook;
import com.biblioteca.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.biblioteca.model.Book.REGULAR_BOOK_FORMAT;

public class BorrowableDefaulterVisitor implements DefaulterVisitor {

    Visitor borrowableVisitor;
    Map<User, String> userBorrowables;

    public BorrowableDefaulterVisitor(Visitor borrowableVisitor, Map<User, String> userBorrowables) {
        this.borrowableVisitor = borrowableVisitor;
        this.userBorrowables = userBorrowables;
    }

    @Override
    public String visitablesAsString() {
        StringBuilder builder = new StringBuilder();
        userBorrowables.forEach((user, borrowableString) -> builder.append(user.toString()).append("\n").append(borrowableString));
        return builder.toString();
    }

    @Override
    public void visit(User user, List<? extends Borrowable> visitables) {
        visitables.forEach(p -> p.accept(borrowableVisitor));
        if (!borrowableVisitor.isEmpty())
            userBorrowables.put(user, borrowableVisitor.visitablesAsString());
        borrowableVisitor = new CheckedoutBookVisitor(new ArrayList<>(), REGULAR_BOOK_FORMAT);
    }

    @Override
    public List<CheckedOutBook> visitables() {
        return null;
    }

    @Override
    public boolean borrowablesFound() {
        return !userBorrowables.isEmpty();
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
