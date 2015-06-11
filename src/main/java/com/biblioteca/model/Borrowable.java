package com.biblioteca.model;

public interface Borrowable {

    boolean isEqualTo(String itemName);

    boolean checkout(Owner owner);

    boolean returnItem(Owner owner);

    boolean isCheckedOut();

}
