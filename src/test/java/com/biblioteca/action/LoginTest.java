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
        Map<String, String> users = new HashMap<>();
        users.put("111-1111", "Sauron");
        login = new Login(users);
    }

    @Test
    public void shouldReturnNullOnInvalidLogin() {
        User user = login.login();

        assertNull(user);
    }

}
