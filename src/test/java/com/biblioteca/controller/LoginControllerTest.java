package com.biblioteca.controller;

import com.biblioteca.action.Login;
import com.biblioteca.console.BibliotecaConsoleIO;
import com.biblioteca.console.View;
import com.biblioteca.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static com.biblioteca.constants.Constants.*;

@RunWith(MockitoJUnitRunner.class)
public class LoginControllerTest {

    Login login;
    LoginController loginController;

    @Mock
    View view;

    @Before
    public void setUp() throws Exception {
        List<User> users = new ArrayList<>();
        User user = new User("111-1111", "sauron", "onering", null);
        users.add(user);
        login = new Login(users);
        loginController = new LoginController(login, view);
    }

    @Test
    public void shouldDisplayMessageRequestingUserForInput() {
        loginController.execute();

        Mockito.verify(view).displayMessage(ENTER_USER_NAME);
    }

}