package com.biblioteca.controller;

import com.biblioteca.action.Login;
import com.biblioteca.listener.Listener;
import com.biblioteca.model.User;
import com.biblioteca.view.View;
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

import static com.biblioteca.constants.Constants.ENTER_LIBRARY_NO;
import static com.biblioteca.constants.Constants.RUNNING;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class LoginControllerTest {

    LoginController loginController;

    @Mock
    Login login;

    @Mock
    View view;

    @Mock
    Listener listener;

    @Rule
    public final TextFromStandardInputStream systemInMock
            = TextFromStandardInputStream.emptyStandardInputStream();

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private User user;

    @Before
    public void setUp() throws Exception {
        System.setOut(new PrintStream(outContent));
        loginController = new LoginController(login, view);
        loginController.addListener(listener);
        user = new User("111-1111", "sauron", "onering", null);
    }

    @Test
    public void shouldDisplayMessageRequestingUserForInput() {
        Mockito.when(login.login(Mockito.anyString(), Mockito.anyString())).thenReturn(user);
        loginController.execute();

        verify(view).displayMessage(ENTER_LIBRARY_NO);
    }

    @Test
    public void shouldRetriveInputFromUser() {
        Mockito.when(login.login(Mockito.anyString(), Mockito.anyString())).thenReturn(user);
        loginController.execute();

        verify(view, times(2)).getString();
    }

    @Test
    public void shouldTryToLoginWithCredentials() {
        List<User> users = new ArrayList<>();
        user = new User("111-1111", "sauron", "onering", null);
        users.add(user);
        login = new Login(users);
        View view = new View(new Scanner(System.in));
        loginController = new LoginController(login, view);
        loginController.addListener(listener);
        systemInMock.provideText("111-1111\nonering\n");

        User actualUser = loginController.execute();
        User expectedUser = new User("111-1111", "sauron", "", null);

        assertEquals(expectedUser, actualUser);
    }

    @Test
    public void shouldUpdateListenerWhenLoginIsSuccessful() {
        List<User> users = new ArrayList<>();
        user = new User("111-1111", "sauron", "onering", null);
        users.add(user);
        login = new Login(users);
        View view = new View(new Scanner(System.in));
        loginController = new LoginController(login, view);
        loginController.addListener(listener);
        systemInMock.provideText("111-1111\nonering\n");

        loginController.execute();

        verify(listener).update(RUNNING);
    }
}