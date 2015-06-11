package com.biblioteca.controller;

import com.biblioteca.action.Login;
import com.biblioteca.console.View;
import com.biblioteca.model.User;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.biblioteca.constants.Constants.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LoginControllerTest {

    Login login;
    LoginController loginController;

    @Mock
    View view;

    @Rule
    public final TextFromStandardInputStream systemInMock
            = TextFromStandardInputStream.emptyStandardInputStream();

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();


    @Before
    public void setUp() throws Exception {
        System.setOut(new PrintStream(outContent));
        List<User> users = new ArrayList<>();
        User user = new User("111-1111", "sauron", "onering", null);
        users.add(user);
        login = new Login(users);
        loginController = new LoginController(login, view);
    }

    @Test
    public void shouldDisplayMessageRequestingUserForInput() {
        loginController.execute();

        verify(view).displayMessage(ENTER_LIBRARY_NO);
    }

    @Test
    public void shouldRetriveInputFromUser() {
        loginController.execute();

        verify(view).getString();
    }

    @Test
    public void shouldTryToLoginWithCredentials() {
        View view = new View(new Scanner(System.in));
        loginController = new LoginController(login, view);

        systemInMock.provideText("111-1111\nonering\n");

        User actualUser = loginController.execute();
        User expectedUser = new User("111-1111", "sauron", "", null);

        assertEquals(expectedUser, actualUser);
    }

}