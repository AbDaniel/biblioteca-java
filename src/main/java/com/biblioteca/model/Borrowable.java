package com.biblioteca.model;

public interface Borrowable {

    String getName();

    boolean checkout();

    boolean returnItem();

    boolean isCheckedOut();

}
