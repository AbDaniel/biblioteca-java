package com.biblioteca.visitor;

import com.biblioteca.model.Borrowable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class BorrowableVisitor<T extends Borrowable> implements Visitor<T> {

    private List<T> borrwables;

    public BorrowableVisitor() {
        this.borrwables = new ArrayList<>();
    }

    @Override
    public void visit(T borrowable) {
        borrwables.add(borrowable);
    }

    public String borrowables(Function<? extends Borrowable, String> format) {
        StringBuilder builder = new StringBuilder();
        borrwables.forEach(borrowable -> builder.append(borrowable.toString(format)).append("\n"));
        return builder.toString();
    }

    public int size() {
        return borrwables.size();
    }

}
