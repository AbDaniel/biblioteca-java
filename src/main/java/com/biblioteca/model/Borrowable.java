package com.biblioteca.model;

import com.biblioteca.visitor.Visitable;

public interface Borrowable extends Visitable {

    boolean isEqualTo(String itemName);

    boolean checkout(User user);

    boolean returnItem(User user);

    boolean isCheckedOut();

}
