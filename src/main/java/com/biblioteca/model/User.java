package com.biblioteca.model;

import java.util.List;

public class User implements Owner {

    private String libraryNo;
    private String name;
    private String password;
    private List<Ownable> ownables;

    public User(String libraryNo, String name, String password, List<Ownable> ownables) {
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

    @Override
    public void addOwnable(Ownable ownable) {
        ownables.add(ownable);
    }

    @Override
    public void removeOwnable(Ownable ownable) {
        ownables.remove(ownable);
    }

    public boolean isValidCredential(String libraryNo, String password) {
        return this.libraryNo.equals(libraryNo) && this.password.equals(password);
    }
}