package com.biblioteca.action;

import com.biblioteca.repository.Accounts;
import com.biblioteca.visitor.DefaulterVisitor;

public class ListDefaulters implements Action {

    private Accounts accounts;
    private DefaulterVisitor visitor;

    public ListDefaulters(Accounts accounts, DefaulterVisitor visitor) {
        this.accounts = accounts;
        this.visitor = visitor;
    }

    @Override
    public void execute() {
        accounts.listDefaulter(visitor);
    }



}
