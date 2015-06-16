package com.biblioteca.model;

import com.biblioteca.visitor.DefaulterVisitor;
import com.biblioteca.visitor.Visitable;
import com.biblioteca.visitor.Visitor;

import java.util.List;

public class User {

    private final String libraryNo;
    private final String name;
    private String password;
    private List<Borrowable> ownables;

    public User(String libraryNo, String name, String password, List<Borrowable> ownables) {
        this.libraryNo = libraryNo;
        this.name = name;
        this.password = password;
        this.ownables = ownables;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return !(libraryNo != null ? !libraryNo.equals(user.libraryNo) : user.libraryNo != null);
    }

    @Override
    public int hashCode() {
        return libraryNo != null ? libraryNo.hashCode() : 0;
    }

    public void addBorrowable(Borrowable ownable) {
        ownables.add(ownable);
    }

    public void removeBorrowable(Borrowable ownable) {
        ownables.remove(ownable);
    }

    public boolean isValidCredential(String libraryNo, String password) {
        return this.libraryNo.equals(libraryNo) && this.password.equals(password);
    }

    public void accept(DefaulterVisitor visitor) {
        visitor.visit(this, ownables);
    }

    @Override
    public String toString() {
        return "libraryNo='" + libraryNo + '\'' +
                ", name='" + name + '\'';
    }
}
