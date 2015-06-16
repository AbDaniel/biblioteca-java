package com.biblioteca.action;

import com.biblioteca.repository.Accounts;
import com.biblioteca.visitor.DefaulterVisitor;

public class ListDefaulters implements Action {

    private final Accounts accounts;
    private final DefaulterVisitor visitor;

    public ListDefaulters(Accounts accounts, DefaulterVisitor visitor) {
        this.accounts = accounts;
        this.visitor = visitor;
    }

    @Override
    public void execute() {
        accounts.listDefaulter(visitor);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListDefaulters that = (ListDefaulters) o;

        if (accounts != null ? !accounts.equals(that.accounts) : that.accounts != null) return false;
        return !(visitor != null ? !visitor.equals(that.visitor) : that.visitor != null);

    }

    @Override
    public int hashCode() {
        int result = accounts != null ? accounts.hashCode() : 0;
        result = 31 * result + (visitor != null ? visitor.hashCode() : 0);
        return result;
    }
}
