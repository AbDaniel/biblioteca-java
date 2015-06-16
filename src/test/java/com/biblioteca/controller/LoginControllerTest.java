package com.biblioteca.controller;

import com.biblioteca.action.Login;
import com.biblioteca.action.Parser;
import com.biblioteca.listener.ExitLogoutListener;
import com.biblioteca.listener.LoginListener;
import com.biblioteca.model.User;
import com.biblioteca.view.MenuView;
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
import java.util.Map;
import java.util.Scanner;

import static com.biblioteca.constants.Constants.ENTER_LIBRARY_NO;
import static com.biblioteca.constants.Constants.RUNNING;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LoginControllerTest {

    LoginController loginController;

    @Mock
    Login login;

    @Mock
    View view;

    @Mock
    ExitLogoutListener listener;

    @Mock
    LoginListener loginListener;

    @Mock
    Map<User, Controller> controllers;

    @Mock
    Parser parser;

    @Mock
    MenuView menuView;

    @Rule
    public final TextFromStandardInputStream systemInMock
            = TextFromStandardInputStream.emptyStandardInputStream();

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private User user;
    private List<User> users;
    private UserController userController;

    @Before
    public void setUp() throws Exception {
        System.setOut(new PrintStream(outContent));
        users = new ArrayList<>();
        user = new User("111-1111", "sauron", "onering", null);
        users.add(user);
        login = new Login(users);
        View view = new View(new Scanner(System.in));
        loginController = new LoginController(login, view, controllers);
        loginController.addExitLogoutListener(listener);
        loginController.addLoginListener(loginListener);
        systemInMock.provideText("111-1111\nonering\n");
    }

    private void setUpWithMocks() {
        login = mock(Login.class);
        view = mock(View.class);
        loginController = new LoginController(login, view, controllers);
        loginController.addExitLogoutListener(listener);
        loginController.addLoginListener(loginListener);
        when(login.login(Mockito.anyString(), Mockito.anyString())).thenReturn(user);
    }

    @Test
    public void shouldDisplayMessageRequestingUserForInput() {
        setUpWithMocks();
        loginController.execute();

        verify(view).displayMessage(ENTER_LIBRARY_NO);
    }

    @Test
    public void shouldRetriveInputFromUser() {
        setUpWithMocks();
        loginController.execute();

        verify(view, times(2)).getString();
    }

    @Test
    public void shouldUpdateListenerWhenLoginIsSuccessful() {
        loginController.execute();

        verify(listener).update(RUNNING);
    }

    @Test
    public void shouldUpdateLoginListenerWhenLoginIsSuccessful() {
        userController = new UserController(menuView, parser);
        when(controllers.get(user)).thenReturn(userController);
        loginController.execute();

        verify(loginListener).update(user, userController);
    }

    @Test
    public void shouldRetriveControllerCorrespondingToTheUser() {
        loginController.execute();

        verify(controllers).get(user);
    }

}