package com.biblioteca.repository;

import com.biblioteca.listener.Listenable;
import com.biblioteca.listener.Listener;
import com.biblioteca.model.User;
import com.biblioteca.visitor.DefaulterVisitor;

import java.util.List;

public class Accounts implements Listenable {

    private final List<User> users;
    private Listener listener;

    public Accounts(List<User> users) {
        this.users = users;
    }

    public void listDefaulter(DefaulterVisitor visitor) {
        users.forEach(user -> user.accept(visitor));
        listener.update(visitor.visitablesAsString());
    }

    @Override
    public void addListener(Listener listener) {
        this.listener = listener;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Accounts accounts = (Accounts) o;

        return !(users != null ? !users.equals(accounts.users) : accounts.users != null);

    }

    @Override
    public int hashCode() {
        return users != null ? users.hashCode() : 0;
    }
}
