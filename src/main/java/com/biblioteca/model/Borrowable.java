package com.biblioteca.model;

import com.biblioteca.visitor.Visitable;

import java.util.function.Function;

public interface Borrowable<T> extends Visitable {

    boolean isEqualTo(String itemName);

    boolean checkout(User user);

    boolean returnItem(User user);

    boolean isCheckedOut();

    String toString(Function<? super T, String> format);

}
