package com.biblioteca.model;

public interface Borrowable {

    boolean isEqualTo(String itemName);

    boolean checkout(User user);

    boolean returnItem(User user);

    boolean isCheckedOut();

}
