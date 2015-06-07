package com.biblioteca.model;

public class Book {

    private final String name;
    private String author;
    private int year;
    private boolean checkedOut;

    public Book(String name, String author, int year) {
        this.name = name;
        this.author = author;
        this.year = year;
        this.checkedOut = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        return !(name != null ? !name.equals(book.name) : book.name != null);

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year;
    }

    public boolean checkout() {
        if (checkedOut) {
            return false;
        } else {
            checkedOut = true;
            return true;
        }
    }

    public boolean returnBook() {
        if (checkedOut) {
            checkedOut = false;
            return true;
        } else {
            return false;
        }
    }

    public boolean isCheckedOut() {
        return checkedOut;
    }
}
