package com.biblioteca.model;

public class User {

    String libraryNo;
    String name;
    char[] password;

    public User(String libraryNo, String name, char[] password) {
        this.libraryNo = libraryNo;
        this.name = name;
        this.password = password;
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
}
