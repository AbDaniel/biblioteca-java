package com.biblioteca.action;

import com.biblioteca.model.User;
import com.biblioteca.search.UserSearcher;

import java.util.List;

public class Login {

    private List<User> users;

    public Login(List<User> users) {
        this.users = users;
    }

    public User login(String librayNumber, String password) {
        return users.stream().filter(p -> p.isValidCredential(librayNumber, password)).findFirst().orElse(null);
    }

    public User login(UserSearcher searcher) {
        users.forEach(user -> user.accept(searcher));
        return searcher.result();
    }

}
