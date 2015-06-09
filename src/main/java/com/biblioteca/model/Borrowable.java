package com.biblioteca.model;

public interface Borrowable {

    String getName();

    boolean checkout(Owner owner);

    boolean returnItem(Owner owner);

    boolean isCheckedOut();

}
