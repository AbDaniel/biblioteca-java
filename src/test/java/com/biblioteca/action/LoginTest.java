package com.biblioteca.action;

import com.biblioteca.model.User;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class LoginTest {

    Login login;

    @Before
    public void setUp() throws Exception {
        List<User> users = new ArrayList<>();
        User user = new User("111-1111", "sauron", "onering", null);
        users.add(user);
        login = new Login(users);
    }

    @Test
    public void shouldReturnNullOnInvalidLogin() {
        String librayNumber = "112";
        String password = "";
        User user = login.login(librayNumber, password);

        assertNull(user);
    }

    @Test
    public void shouldReturnUserOnValidLogin() {
        String librayNumber = "111-1111";
        String password = "onering";
        User actualUser = login.login(librayNumber, password);

        User expectedUser = new User("111-1111", "", "", null);
        assertEquals(actualUser, expectedUser);
    }

}
